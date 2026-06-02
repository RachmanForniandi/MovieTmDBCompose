package rachman.forniandi.movietmdbcompose.data.local.source

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.movietmdbcompose.data.local.dao.FavoriteMovieDao
import rachman.forniandi.movietmdbcompose.data.local.entity.FavoriteMovieEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) {

    fun getAllFavorites(): Flow<List<FavoriteMovieEntity>> {
        return favoriteMovieDao.getAllFavorites()
    }

    suspend fun getFavoriteById(movieId: Int): FavoriteMovieEntity? {
        return favoriteMovieDao.getFavoriteById(movieId)
    }

    suspend fun insertFavorite(movie: FavoriteMovieEntity) {
        favoriteMovieDao.insertFavorite(movie)
    }

    suspend fun deleteFavoriteById(movieId: Int) {
        favoriteMovieDao.deleteFavoriteById(movieId)
    }

    fun isFavorite(movieId: Int): Flow<Boolean> {
        return favoriteMovieDao.isFavorite(movieId)
    }
}