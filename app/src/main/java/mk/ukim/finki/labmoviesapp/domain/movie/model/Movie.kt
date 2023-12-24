package mk.ukim.finki.labmoviesapp.domain.movie.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true) var movieId: Int,
    var name: String,
    var description: String,
    var director: String,
)




