package rachman.forniandi.movietmdbcompose.presentation.navigation

import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun MovieNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MovieList.route,
        modifier = modifier
    ) {

        composable(route = Screen.MovieList.route) {
            MovieListScreen(
                onMovieClick = { movieId ->
                    navController.navigate(Screen.MovieDetail.createRoute(movieId))
                },
                onFavoriteClick = {
                    navController.navigate(Screen.Favorite.route) {
                        launchSingleTop = true
                    }
                },
                onAboutClick = {
                    navController.navigate(Screen.About.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = Screen.MovieDetail.route,
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable
            MovieDetailScreen(
                movieId = movieId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(route = Screen.Favorite.route) {
            FavoriteScreen(
                onMovieClick = { movieId ->
                    navController.navigate(Screen.MovieDetail.createRoute(movieId))
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(route = Screen.About.route) {
            AboutScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
