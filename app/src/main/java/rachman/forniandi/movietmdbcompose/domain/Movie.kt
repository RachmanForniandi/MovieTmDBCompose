package rachman.forniandi.movietmdbcompose.domain

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val releaseDate: String?,
    val genreIds: List<Int>,
    val popularity: Double,
    val originalLanguage: String,
    val isFavorite: Boolean = false
)

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val releaseDate: String?,
    val genres: List<Genre>,
    val popularity: Double,
    val originalLanguage: String,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val budget: Long,
    val revenue: Long,
    val productionCompanies: List<String>,
    val isFavorite: Boolean = false
)

data class Genre(
    val id: Int,
    val name: String
)

