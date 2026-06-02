package rachman.forniandi.movietmdbcompose.data.remote

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class MovieDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("adult") val adult: Boolean
)

data class MovieDetailDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("genres") val genres: List<GenreDto>,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("status") val status: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("budget") val budget: Long,
    @SerializedName("revenue") val revenue: Long,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanyDto>?,

)

data class GenreListResponse(
    @SerializedName("genres") val genres: List<GenreDto>
)

data class GenreDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class ProductionCompanyDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("origin_country") val originCountry: String
)


