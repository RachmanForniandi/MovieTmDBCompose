package rachman.forniandi.movietmdbcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import rachman.forniandi.movietmdbcompose.data.local.dao.FavoriteMovieDao
import rachman.forniandi.movietmdbcompose.data.local.entity.FavoriteMovieEntity

@Database(
    entities = [FavoriteMovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao

    companion object {
        const val DATABASE_NAME = "movie_database"
    }
}