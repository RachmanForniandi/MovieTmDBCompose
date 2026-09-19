package rachman.forniandi.movietmdbcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.movietmdbcompose.domain.interactor.FavoriteMovieInteractor
import rachman.forniandi.movietmdbcompose.domain.interactor.MovieInteractor
import rachman.forniandi.movietmdbcompose.domain.usecase.FavoriteMovieUseCase
import rachman.forniandi.movietmdbcompose.domain.usecase.MovieUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindMovieUseCase(
        interactor: MovieInteractor
    ): MovieUseCase

    @Binds
    @Singleton
    abstract fun bindFavoriteMovieUseCase(
        interactor: FavoriteMovieInteractor
    ): FavoriteMovieUseCase

}