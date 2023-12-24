package mk.ukim.finki.labmoviesapp.domain.movie.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Actor(
    @PrimaryKey(autoGenerate = true)
    var actorId: Int,
    var name: String
)


