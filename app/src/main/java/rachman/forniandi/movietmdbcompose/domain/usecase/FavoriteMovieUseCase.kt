package rachman.forniandi.movietmdbcompose.domain.usecase

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.movietmdbcompose.domain.Movie

interface FavoriteMovieUseCase {
    fun getAllFavorites(): Flow<List<Movie>>
    fun isFavorite(movieId: Int): Flow<Boolean>
    suspend fun addFavorite(movie: Movie)
    suspend fun removeFavorite(movieId: Int)
}