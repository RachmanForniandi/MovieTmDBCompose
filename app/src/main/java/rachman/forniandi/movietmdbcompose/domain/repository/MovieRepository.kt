package rachman.forniandi.movietmdbcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.movietmdbcompose.domain.Genre
import rachman.forniandi.movietmdbcompose.domain.Movie
import rachman.forniandi.movietmdbcompose.domain.MovieDetail
import rachman.forniandi.movietmdbcompose.utils.RemoteResponse

interface MovieRepository {
    fun getPopularMovies(): Flow<RemoteResponse<List<Movie>>>
    fun searchMovies(query: String): Flow<RemoteResponse<List<Movie>>>
    fun getMovieDetail(movieId: Int): Flow<RemoteResponse<MovieDetail>>
    fun getMovieGenres(): Flow<RemoteResponse<List<Genre>>>


    fun getAllFavorites(): Flow<List<Movie>>
    fun isFavorite(movieId: Int): Flow<Boolean>
    suspend fun addFavorite(movie: Movie)
    suspend fun removeFavorite(movieId: Int)

}