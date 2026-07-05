package rachman.forniandi.movietmdbcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import rachman.forniandi.movietmdbcompose.domain.Movie
import rachman.forniandi.movietmdbcompose.domain.usecase.FavoriteMovieUseCase
import rachman.forniandi.movietmdbcompose.domain.usecase.MovieUseCase
import rachman.forniandi.movietmdbcompose.utils.RemoteResponse
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {

    private val _moviesState = MutableStateFlow<RemoteResponse<List<Movie>>>(RemoteResponse.Loading())
    val moviesState: StateFlow<RemoteResponse<List<Movie>>> = _moviesState.asStateFlow()

    private val _genreMap = MutableStateFlow<Map<Int, String>>(emptyMap())
    val genreMap: StateFlow<Map<Int, String>> = _genreMap.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()


    private var searchJob: Job? = null

    init {
        loadPopularMovies()
        loadGenres()
        observeSearch()
    }

    private fun loadPopularMovies() {
        viewModelScope.launch {
            movieUseCase.getPopularMovies().collect { response ->
                _moviesState.value = response
            }
        }
    }

    private fun loadGenres() {
        viewModelScope.launch {
            movieUseCase.getMovieGenres().collect { response ->
                if (response is RemoteResponse.Success) {
                    _genreMap.value = response.data
                        ?.associate { it.id to it.name }
                        ?: emptyMap()
                }
            }
        }
    }

    private fun observeSearch() {
        viewModelScope.launch {
            _searchQuery
                .debounce(400)
                .distinctUntilChanged()
                .collect { query ->
                    searchJob?.cancel()
                    searchJob = launch {
                        if (query.isBlank()) {
                            loadPopularMovies()
                        } else {
                            movieUseCase.searchMovies(query).collect { response ->
                                _moviesState.value = response
                            }
                        }
                    }
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }


}