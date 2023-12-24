package mk.ukim.finki.labmoviesapp.domain.movie.model

import androidx.room.Entity

@Entity(primaryKeys = ["actorId", "movieId"])
class ActorMovieCrossRef(
    var movieId: Int,
    var actorId: Int
)

