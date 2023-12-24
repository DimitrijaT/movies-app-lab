package mk.ukim.finki.labmoviesapp.ui.movies

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.labmoviesapp.domain.movie.repository.MovieRepository
import mk.ukim.finki.labmoviesapp.domain.movie.room.AppDatabase
import mk.ukim.finki.labmoviesapp.domain.movie.room.RoomMovieDatasource

class MoviesViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(
            MovieRepository(
                RoomMovieDatasource(
                    AppDatabase.getDatabase(context).movieDao()
                )
            )
        )
    }
}

