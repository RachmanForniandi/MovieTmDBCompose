package rachman.forniandi.movietmdbcompose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int,

    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "genre_ids")
    val genreIds: String, // stored as comma-separated string

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "added_at")
    val addedAt: Long = System.currentTimeMillis()
)