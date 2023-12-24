package mk.ukim.finki.labmoviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mk.ukim.finki.labmoviesapp.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp.domain.movie.model.MovieWithActors
import mk.ukim.finki.labmoviesapp.domain.movie.repository.MovieRepository

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val moviesLiveData = MutableLiveData<List<Movie>>()

    fun getMoviesLiveData(): LiveData<List<Movie>> {
        return moviesLiveData
    }


    fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movies: List<Movie> = movieRepository.queryMovies(query)
            moviesLiveData.postValue(movies)
        }
    }

    fun listAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies: List<Movie> = movieRepository.listMovies()
            moviesLiveData.postValue(movies)
        }
    }

    fun getMovieWithActors(movieId: Int): LiveData<MovieWithActors> {
        val movieWithActorsLiveData = MutableLiveData<MovieWithActors>()
        viewModelScope.launch(Dispatchers.IO) {
            val movieWithActors = movieRepository.getMovieWithActors(movieId)
            movieWithActorsLiveData.postValue(movieWithActors)
        }
        return movieWithActorsLiveData
    }

    fun addMovie(movieName: String, movieDirector: String, movieDescription: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.addMovie(movieName, movieDirector, movieDescription)
            val movies: List<Movie> = movieRepository.listMovies()
            moviesLiveData.postValue(movies)
        }
    }


}

