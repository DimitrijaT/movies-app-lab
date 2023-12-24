package mk.ukim.finki.labmoviesapp.domain.movie.room

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mk.ukim.finki.labmoviesapp.domain.movie.model.Actor
import mk.ukim.finki.labmoviesapp.domain.movie.model.ActorMovieCrossRef
import mk.ukim.finki.labmoviesapp.domain.movie.model.Movie


@Database(
    entities = [Movie::class, Actor::class, ActorMovieCrossRef::class],
    version = 2,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao


    // companion object in Kotlin = static in Java
    companion object {

        private var INSTANCE: AppDatabase? = null

        @JvmStatic // jvm to know this is a static function
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context, AppDatabase::class.java, "movies_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }


    }
}

