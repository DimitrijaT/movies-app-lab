package mk.ukim.finki.labmoviesapp.ui.movies.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.labmoviesapp.R
import mk.ukim.finki.labmoviesapp.databinding.FragmentMovieDetailsBinding
import mk.ukim.finki.labmoviesapp.ui.movies.MoviesViewModel
import mk.ukim.finki.labmoviesapp.ui.movies.MoviesViewModelFactory


class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private var _binding: FragmentMovieDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailsBinding.bind(view)

        val movieId = arguments?.getInt("MOVIE_ID")

        val viewModelFactory = MoviesViewModelFactory(requireContext())
        moviesViewModel =
            ViewModelProvider(owner = this, factory = viewModelFactory)[MoviesViewModel::class.java]

        if (movieId != null) {
            moviesViewModel.getMovieWithActors(movieId).observe(viewLifecycleOwner) {
                binding.movieTitle.text = it.movie.name
                binding.movieDirector.text = it.movie.director
                binding.movieDescription.text = it.movie.description
                binding.movieActors.text = it.actors.joinToString { actor -> actor.name }
            }
        }


    }

}