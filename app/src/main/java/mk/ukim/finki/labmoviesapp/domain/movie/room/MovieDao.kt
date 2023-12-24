package mk.ukim.finki.labmoviesapp.domain.movie.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import mk.ukim.finki.labmoviesapp.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp.domain.movie.model.MovieWithActors


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("DELETE FROM movie WHERE movieId = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE name LIKE '%' ||:query || '%' ")
    suspend fun searchMovies(query: String): List<Movie>

    @Transaction
    @Query("SELECT * FROM Movie WHERE movieId = :id")
    fun getMovieWithActors(id: Int): MovieWithActors

    @Query("INSERT INTO movie (name, director, description) VALUES (:movieName, :movieDirector, :movieDescription)")
    suspend fun addMovie(movieName: String, movieDirector: String, movieDescription: String)

}


