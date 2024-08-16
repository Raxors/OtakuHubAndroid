package com.raxors.otakuhub.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raxors.otakuhub.ui.screens.anime_list.AnimeListScreen
import com.raxors.otakuhub.ui.screens.auth.AuthScreen
import com.raxors.otakuhub.ui.screens.main.MainScreen

@Composable
fun RootNav(
    modifier: Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.AuthScreen.route
    ) {
        composable(route = AppScreen.AuthScreen.route) {
            AuthScreen(navController = navController)
        }

        composable(route = AppScreen.MainNavigation.route) {
            MainScreen(
                modifier = modifier,
                logout = {
                    navController.navigate(AppScreen.AuthNavigation.route) {
                        popUpTo(0) {}
                    }
                }
            )
        }
    }
}

@Composable
fun MainNavGraph(
    navController: NavHostController,
    logout: () -> Unit
) {
    NavHost(
        navController = navController,
        route = AppScreen.MainNavigation.route,
        startDestination = AppScreen.AnimeListScreen.route
    ) {
        composable(route = AppScreen.AnimeListScreen.route) {
            AnimeListScreen(navController = navController)
        }

//        composable(route = AppScreen.ProfileScreen.route){
//            ProfileScreen(navController = navController)
//        }
//
//        composable(route = AppScreen.FavouritesScreen.route){
//            ProfileScreen(navController = navController, logout = logout)
//        }
    }
}