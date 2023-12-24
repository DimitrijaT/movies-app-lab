package mk.ukim.finki.labmoviesapp.domain.movie.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithActors(
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "actorId",
        associateBy = Junction(ActorMovieCrossRef::class)
    )
    val actors: List<Actor>
)


