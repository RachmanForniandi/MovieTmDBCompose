package rachman.forniandi.movietmdbcompose.domain.interactor

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.movietmdbcompose.domain.Genre
import rachman.forniandi.movietmdbcompose.domain.Movie
import rachman.forniandi.movietmdbcompose.domain.MovieDetail
import rachman.forniandi.movietmdbcompose.domain.repository.MovieRepository
import rachman.forniandi.movietmdbcompose.domain.usecase.MovieUseCase
import rachman.forniandi.movietmdbcompose.utils.RemoteResponse
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val repository: MovieRepository
): MovieUseCase {
    override fun getPopularMovies(): Flow<RemoteResponse<List<Movie>>> =
        repository.getPopularMovies()

    override fun searchMovies(query: String): Flow<RemoteResponse<List<Movie>>> =
        repository.searchMovies(query)

    override fun getMovieDetail(movieId: Int): Flow<RemoteResponse<MovieDetail>> =
        repository.getMovieDetail(movieId)

    override fun getMovieGenres(): Flow<RemoteResponse<List<Genre>>> =
        repository.getMovieGenres()

}