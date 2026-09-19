package rachman.forniandi.movietmdbcompose.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import rachman.forniandi.movietmdbcompose.data.local.source.LocalDataSource
import rachman.forniandi.movietmdbcompose.data.remote.source.RemoteDataSource
import rachman.forniandi.movietmdbcompose.domain.Genre
import rachman.forniandi.movietmdbcompose.domain.Movie
import rachman.forniandi.movietmdbcompose.domain.MovieDetail
import rachman.forniandi.movietmdbcompose.domain.repository.MovieRepository
import rachman.forniandi.movietmdbcompose.utils.RemoteResponse
import rachman.forniandi.movietmdbcompose.utils.toDomain
import rachman.forniandi.movietmdbcompose.utils.toEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MovieRepository {
    override fun getPopularMovies(): Flow<RemoteResponse<List<Movie>>> = flow {
        emit(RemoteResponse.Loading())
        try {
            val movies = remoteDataSource.getPopularMovies().results.map { it.toDomain() }
            emit(RemoteResponse.Success(movies))
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun searchMovies(query: String): Flow<RemoteResponse<List<Movie>>> = flow {
        emit(RemoteResponse.Loading())
        try {
            val movies = remoteDataSource.searchMovies(query).results.map { it.toDomain() }
            emit(RemoteResponse.Success(movies))
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getMovieDetail(movieId: Int): Flow<RemoteResponse<MovieDetail>> = flow {
        emit(RemoteResponse.Loading())
        try {
            val detail = remoteDataSource.getMovieDetail(movieId).toDomain()
            emit(RemoteResponse.Success(detail))
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getMovieGenres(): Flow<RemoteResponse<List<Genre>>> = flow {
        emit(RemoteResponse.Loading())
        try {
            val genres = remoteDataSource.getMovieGenres().toDomain()
            emit(RemoteResponse.Success(genres))
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message ?: "Unknown error occurred"))
        }
    }

    override fun getAllFavorites(): Flow<List<Movie>> =
        localDataSource.getAllFavorites().map { entities -> entities.map { it.toDomain() } }

    override fun isFavorite(movieId: Int): Flow<Boolean> =
        localDataSource.isFavorite(movieId)

    override suspend fun addFavorite(movie: Movie) =
        localDataSource.insertFavorite(movie.toEntity())

    override suspend fun removeFavorite(movieId: Int) =
        localDataSource.deleteFavoriteById(movieId)
}