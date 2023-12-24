package mk.ukim.finki.labmoviesapp.ui.movies.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import mk.ukim.finki.labmoviesapp.R
import mk.ukim.finki.labmoviesapp.adapter.MovieAdapter
import mk.ukim.finki.labmoviesapp.databinding.FragmentMovieListBinding
import mk.ukim.finki.labmoviesapp.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp.ui.movies.MoviesViewModel
import mk.ukim.finki.labmoviesapp.ui.movies.MoviesViewModelFactory
import mk.ukim.finki.labmoviesapp.ui.movies.dialog.AddNewMovieDialogFragment

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {


    // FragmentMovieListBinding == fragment_movie_list.xml
    // Autoconverted to Kotlin
    private var _binding: FragmentMovieListBinding? = null

    private val binding get() = _binding!!

    public lateinit var moviesViewModel: MoviesViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieListBinding.bind(view)

        val viewModelFactory = MoviesViewModelFactory(requireContext())
        moviesViewModel =
            ViewModelProvider(owner = this, factory = viewModelFactory)[MoviesViewModel::class.java]

        var adapter: MovieAdapter =
            MovieAdapter(requireContext(), ArrayList<Movie>(), parentFragmentManager)
        binding.list.adapter = adapter


        moviesViewModel.getMoviesLiveData().observe(viewLifecycleOwner) {
            adapter.updateMovies(it)
        }

        binding.button.setOnClickListener {
            val query = binding.editQuery.text.toString()
            if (query.isEmpty()) {
                Snackbar.make(view, R.string.please_enter_query, Snackbar.LENGTH_LONG).show()
            } else {
                moviesViewModel.search(query)
            }
        }

        binding.addNewMovieButton.setOnClickListener {
            AddNewMovieDialogFragment().show(parentFragmentManager, "add_new_movie")
        }


        if (moviesViewModel.listAll() != null) {
            moviesViewModel.listAll()
        }

    }

}