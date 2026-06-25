package rachman.forniandi.movietmdbcompose.domain.usecase

import rachman.forniandi.movietmdbcompose.domain.Genre
import rachman.forniandi.movietmdbcompose.domain.Movie
import rachman.forniandi.movietmdbcompose.domain.MovieDetail
import rachman.forniandi.movietmdbcompose.utils.RemoteResponse

interface MovieUseCase {
    suspend fun getPopularMovies(): RemoteResponse<List<Movie>>
    suspend fun searchMovies(query: String): RemoteResponse<List<Movie>>
    suspend fun getMovieDetail(movieId: Int): RemoteResponse<MovieDetail>
    suspend fun getMovieGenres(): RemoteResponse<List<Genre>>
}