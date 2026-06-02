package rachman.forniandi.movietmdbcompose.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.movietmdbcompose.data.local.MovieDatabase
import rachman.forniandi.movietmdbcompose.data.local.dao.FavoriteMovieDao
import rachman.forniandi.movietmdbcompose.data.local.source.LocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {
    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteMovieDao(database: MovieDatabase): FavoriteMovieDao {
        return database.favoriteMovieDao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(favoriteMovieDao: FavoriteMovieDao): LocalDataSource {
        return LocalDataSource(favoriteMovieDao)
    }
}