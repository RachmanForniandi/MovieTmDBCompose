package rachman.forniandi.movietmdbcompose.domain.interactor

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.movietmdbcompose.domain.Movie
import rachman.forniandi.movietmdbcompose.domain.repository.MovieRepository
import rachman.forniandi.movietmdbcompose.domain.usecase.FavoriteMovieUseCase
import javax.inject.Inject

class FavoriteMovieInteractor @Inject constructor(
    private val repository: MovieRepository
) : FavoriteMovieUseCase {

    override fun getAllFavorites(): Flow<List<Movie>> {
        return repository.getAllFavorites()
    }

    override fun isFavorite(movieId: Int): Flow<Boolean> {
        return repository.isFavorite(movieId)
    }

    override suspend fun addFavorite(movie: Movie) {
        repository.addFavorite(movie)
    }

    override suspend fun removeFavorite(movieId: Int) {
        repository.removeFavorite(movieId)
    }
}
