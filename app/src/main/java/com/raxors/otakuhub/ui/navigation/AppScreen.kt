package com.raxors.otakuhub.ui.navigation

//TODO Когда выйдет стабильный compose-navigation с объектами перейти на него
sealed class AppScreen(val route: String) {
    data object AuthScreen : AppScreen("auth_screen")
    data object MainScreen : AppScreen("main_screen")
    data object ProfileScreen : AppScreen("profile_screen")
    data object SettingsScreen : AppScreen("settings_screen")
    data object FavouritesScreen : AppScreen("favourites_screen")
    data object AnimeListScreen : AppScreen("anime_list_screen")

    data object AuthNavigation : AppScreen("AUTH_NAV_GRAPH")
    data object MainNavigation : AppScreen("HOME_NAV_GRAPH")
}