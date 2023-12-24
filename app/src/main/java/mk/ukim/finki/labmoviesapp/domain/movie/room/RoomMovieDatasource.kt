package mk.ukim.finki.labmoviesapp.domain.movie.room

import mk.ukim.finki.labmoviesapp.domain.movie.LocalMovieDatasource
import mk.ukim.finki.labmoviesapp.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp.domain.movie.model.MovieWithActors

class RoomMovieDatasource(private val movieDao: MovieDao) : LocalMovieDatasource {
    override suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    override suspend fun saveAll(movies: List<Movie>) {
        for (movie in movies) {
            movieDao.insert(movie)
        }
    }

    override suspend fun delete(id: Int) {
        movieDao.delete(id)
    }

    override suspend fun getAll(): List<Movie> {
        return movieDao.getAll()
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        return movieDao.searchMovies(query)
    }

    override suspend fun getMovieWithActors(movieId: Int): MovieWithActors {
        return movieDao.getMovieWithActors(movieId)
    }

    override suspend fun addMovie(movieName: String, movieDirector: String, movieDescription: String) {
        movieDao.addMovie(movieName, movieDirector, movieDescription)
    }

}


