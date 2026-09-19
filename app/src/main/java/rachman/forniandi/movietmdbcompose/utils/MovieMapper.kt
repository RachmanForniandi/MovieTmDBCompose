package rachman.forniandi.movietmdbcompose.utils

import rachman.forniandi.movietmdbcompose.data.local.entity.FavoriteMovieEntity
import rachman.forniandi.movietmdbcompose.data.remote.GenreDto
import rachman.forniandi.movietmdbcompose.data.remote.GenreListResponse
import rachman.forniandi.movietmdbcompose.data.remote.MovieDetailDto
import rachman.forniandi.movietmdbcompose.data.remote.MovieDto
import rachman.forniandi.movietmdbcompose.domain.Genre
import rachman.forniandi.movietmdbcompose.domain.Movie
import rachman.forniandi.movietmdbcompose.domain.MovieDetail

fun MovieDto.toDomain(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    releaseDate = releaseDate,
    genreIds = genreIds,
    popularity = popularity,
    originalLanguage = originalLanguage
)

fun MovieDetailDto.toDomain(): MovieDetail = MovieDetail(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    releaseDate = releaseDate,
    genres = genres.map { it.toDomain() },
    popularity = popularity,
    originalLanguage = originalLanguage,
    runtime = runtime,
    status = status,
    tagline = tagline,
    budget = budget,
    revenue = revenue,
    productionCompanies = productionCompanies?.map { it.name } ?: emptyList()
)

fun GenreDto.toDomain(): Genre = Genre(id = id, name = name)

fun GenreListResponse.toDomain(): List<Genre> = genres.map { it.toDomain() }

// ─── Local Entity ↔ Domain ───────────────────────────────────────────────────

fun FavoriteMovieEntity.toDomain(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    releaseDate = releaseDate,
    genreIds = genreIds.split(",").mapNotNull { it.trim().toIntOrNull() },
    popularity = popularity,
    originalLanguage = originalLanguage,
    isFavorite = true
)

fun Movie.toEntity(): FavoriteMovieEntity = FavoriteMovieEntity(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    releaseDate = releaseDate,
    genreIds = genreIds.joinToString(","),
    popularity = popularity,
    originalLanguage = originalLanguage
)

// ─── Domain MovieDetail → Domain Movie (untuk simpan favorit dari detail) ────

fun MovieDetail.toMovie(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    releaseDate = releaseDate,
    genreIds = genres.map { it.id },
    popularity = popularity,
    originalLanguage = originalLanguage
)
