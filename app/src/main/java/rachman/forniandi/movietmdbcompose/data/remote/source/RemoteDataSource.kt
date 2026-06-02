package rachman.forniandi.movietmdbcompose.data.remote.source

import jakarta.inject.Inject
import rachman.forniandi.movietmdbcompose.BuildConfig
import rachman.forniandi.movietmdbcompose.data.remote.APIService
import rachman.forniandi.movietmdbcompose.data.remote.GenreListResponse
import rachman.forniandi.movietmdbcompose.data.remote.MovieDetailDto
import rachman.forniandi.movietmdbcompose.data.remote.MovieListResponse

class RemoteDataSource @Inject constructor(
    private val apiService: APIService
) {

    suspend fun getPopularMovies(): MovieListResponse {
        return apiService.getPopularMovies(
            apiKey = BuildConfig.TMDB_API_KEY
        )
    }

    suspend fun searchMovies(query: String): MovieListResponse {
        return apiService.searchMovies(
            apiKey = BuildConfig.TMDB_API_KEY,
            query = query
        )
    }

    suspend fun getMovieDetail(movieId: Int): MovieDetailDto {
        return apiService.getMovieDetail(
            movieId = movieId,
            apiKey = BuildConfig.TMDB_API_KEY
        )
    }

    suspend fun getMovieGenres(): GenreListResponse {
        return apiService.getMovieGenres(
            apiKey = BuildConfig.TMDB_API_KEY
        )
    }
}