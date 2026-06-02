package rachman.forniandi.movietmdbcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import rachman.forniandi.movietmdbcompose.data.local.entity.FavoriteMovieEntity

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorite_movies ORDER BY added_at DESC")
    fun getAllFavorites(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    suspend fun getFavoriteById(movieId: Int): FavoriteMovieEntity?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertFavorite(movie: FavoriteMovieEntity)

    @Delete
    suspend fun deleteFavorite(movie: FavoriteMovieEntity)

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    suspend fun deleteFavoriteById(movieId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_movies WHERE id = :movieId)")
    fun isFavorite(movieId: Int): Flow<Boolean>
}