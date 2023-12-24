package mk.ukim.finki.labmoviesapp.domain.movie.repository

import mk.ukim.finki.labmoviesapp.domain.movie.LocalMovieDatasource
import mk.ukim.finki.labmoviesapp.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp.domain.movie.model.MovieWithActors


class MovieRepository(
    private val localMovieDatasource: LocalMovieDatasource,
) {

    suspend fun queryMovies(query: String): List<Movie> {
        return localMovieDatasource.searchMovies(query)
    }


    suspend fun listMovies(): List<Movie> {
        return localMovieDatasource.getAll()
    }

    suspend fun getMovieWithActors(movieId: Int): MovieWithActors {
        return localMovieDatasource.getMovieWithActors(movieId)
    }

    suspend fun addMovie(movieName: String, movieDirector: String, movieDescription: String) {
        localMovieDatasource.addMovie(movieName, movieDirector, movieDescription)
    }

}


