package rachman.forniandi.movietmdbcompose.presentation.navigation

sealed class Screen(val route: String) {

    data object MovieList : Screen("movie_list")

    data object MovieDetail : Screen("movie_detail/{movieId}") {
        fun createRoute(movieId: Int) = "movie_detail/$movieId"
    }

    data object Favorite : Screen("favorite")

    data object About : Screen("about")
}
