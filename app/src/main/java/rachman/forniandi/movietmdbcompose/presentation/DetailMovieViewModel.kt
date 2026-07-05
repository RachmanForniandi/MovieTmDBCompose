package rachman.forniandi.movietmdbcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rachman.forniandi.movietmdbcompose.domain.MovieDetail
import rachman.forniandi.movietmdbcompose.domain.usecase.FavoriteMovieUseCase
import rachman.forniandi.movietmdbcompose.domain.usecase.MovieUseCase
import rachman.forniandi.movietmdbcompose.utils.RemoteResponse
import rachman.forniandi.movietmdbcompose.utils.toMovie
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModel() {

    private val _detailState = MutableStateFlow<RemoteResponse<MovieDetail>>(RemoteResponse.Loading())
    val detailState: StateFlow<RemoteResponse<MovieDetail>> = _detailState.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    fun loadMovieDetail(movieId: Int) {
        viewModelScope.launch {
            movieUseCase.getMovieDetail(movieId).collect { response ->
                _detailState.value = response

                if (response is RemoteResponse.Success) {
                    observeFavoriteStatus(movieId)
                }
            }
        }
    }

    private fun observeFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            favoriteMovieUseCase.isFavorite(movieId).collect { isFav ->
                _isFavorite.value = isFav
            }
        }
    }

    fun toggleFavorite(movieDetail: MovieDetail) {
        viewModelScope.launch {
            if (_isFavorite.value) {
                favoriteMovieUseCase.removeFavorite(movieDetail.id)
            } else {
                favoriteMovieUseCase.addFavorite(movieDetail.toMovie())
            }
        }
    }
}
