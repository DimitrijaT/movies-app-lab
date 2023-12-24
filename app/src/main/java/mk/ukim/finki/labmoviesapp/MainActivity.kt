package mk.ukim.finki.labmoviesapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.labmoviesapp.databinding.FragmentMovieListBinding
import mk.ukim.finki.labmoviesapp.ui.movies.MoviesViewModel
import mk.ukim.finki.labmoviesapp.ui.movies.MoviesViewModelFactory
import mk.ukim.finki.labmoviesapp.ui.movies.dialog.AddNewMovieDialogFragment
import mk.ukim.finki.labmoviesapp.ui.movies.fragments.MovieListFragment

class MainActivity : AppCompatActivity(), AddNewMovieDialogFragment.AddNewMovieDialogListener {
    private lateinit var moviesViewModel: MoviesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = MoviesViewModelFactory(this)
        moviesViewModel =
            ViewModelProvider(owner = this, factory = viewModelFactory)[MoviesViewModel::class.java]

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, MovieListFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }


    }

    override fun OnDialogPositiveClick(
        movieName: String,
        movieDirector: String,
        movieDescription: String
    ) {
        moviesViewModel.addMovie(movieName, movieDirector, movieDescription)
        // refresh MovieListFragment:
        supportFragmentManager.popBackStack()
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, MovieListFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
        }

    }
}