package rachman.forniandi.movietmdbcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.movietmdbcompose.domain.Genre
import rachman.forniandi.movietmdbcompose.domain.Movie
import rachman.forniandi.movietmdbcompose.domain.MovieDetail
import rachman.forniandi.movietmdbcompose.utils.RemoteResponse

interface MovieRepository {
    suspend fun getPopularMovies(): RemoteResponse<List<Movie>>
    suspend fun searchMovies(query: String): RemoteResponse<List<Movie>>
    suspend fun getMovieDetail(movieId: Int): RemoteResponse<MovieDetail>
    suspend fun getMovieGenres(): RemoteResponse<List<Genre>>
    fun getAllFavorites(): Flow<List<Movie>>
    fun isFavorite(movieId: Int): Flow<Boolean>
    suspend fun addFavorite(movie: Movie)
    suspend fun removeFavorite(movieId: Int)

}