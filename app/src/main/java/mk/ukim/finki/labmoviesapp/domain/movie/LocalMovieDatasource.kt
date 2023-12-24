package mk.ukim.finki.labmoviesapp.domain.movie

import mk.ukim.finki.labmoviesapp.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp.domain.movie.model.MovieWithActors

interface LocalMovieDatasource {

    suspend fun insert(movie: Movie)

    suspend fun saveAll(movies: List<Movie>)

    suspend fun delete(id: Int)

    suspend fun getAll(): List<Movie>

    suspend fun searchMovies(query: String): List<Movie>

    suspend fun getMovieWithActors(movieId: Int): MovieWithActors

    suspend fun addMovie(movieName: String, movieDirector: String, movieDescription: String)
}


