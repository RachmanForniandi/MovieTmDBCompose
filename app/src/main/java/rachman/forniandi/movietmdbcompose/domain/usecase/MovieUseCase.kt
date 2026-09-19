package rachman.forniandi.movietmdbcompose.domain.usecase

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.movietmdbcompose.domain.Genre
import rachman.forniandi.movietmdbcompose.domain.Movie
import rachman.forniandi.movietmdbcompose.domain.MovieDetail
import rachman.forniandi.movietmdbcompose.utils.RemoteResponse

interface MovieUseCase {
    fun getPopularMovies(): Flow<RemoteResponse<List<Movie>>>
    fun searchMovies(query: String): Flow<RemoteResponse<List<Movie>>>
    fun getMovieDetail(movieId: Int): Flow<RemoteResponse<MovieDetail>>
    fun getMovieGenres(): Flow<RemoteResponse<List<Genre>>>



}