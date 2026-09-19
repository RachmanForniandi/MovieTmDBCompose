package rachman.forniandi.movietmdbcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rachman.forniandi.movietmdbcompose.domain.Movie
import rachman.forniandi.movietmdbcompose.domain.usecase.FavoriteMovieUseCase
import rachman.forniandi.movietmdbcompose.utils.RemoteResponse
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModel() {

    private val _favoritesState = MutableStateFlow<RemoteResponse<List<Movie>>>(RemoteResponse.Loading())
    val favoritesState: StateFlow<RemoteResponse<List<Movie>>> = _favoritesState.asStateFlow()

    init {
        getFavoriteMovies()
    }

    fun getFavoriteMovies() {
        viewModelScope.launch {
            _favoritesState.value = RemoteResponse.Loading()
            favoriteMovieUseCase.getAllFavorites().collect { movies ->
                _favoritesState.value = RemoteResponse.Success(movies)
            }
        }
    }

    fun addFavorite(movie: Movie) {
        viewModelScope.launch { favoriteMovieUseCase.addFavorite(movie) }
    }

    fun removeFavorite(movieId: Int) {
        viewModelScope.launch { favoriteMovieUseCase.removeFavorite(movieId) }
    }

    fun isFavorite(movieId: Int): StateFlow<Boolean> {
        val state = MutableStateFlow(false)
        viewModelScope.launch {
            favoriteMovieUseCase.isFavorite(movieId).collect { isFav ->
                state.value = isFav
            }
        }
        return state.asStateFlow()
    }
}
