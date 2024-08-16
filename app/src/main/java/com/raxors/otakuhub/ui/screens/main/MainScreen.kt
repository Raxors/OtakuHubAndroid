package com.raxors.otakuhub.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raxors.otakuhub.ui.navigation.AppScreen
import com.raxors.otakuhub.ui.navigation.MainNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    modifier: Modifier,
    logout: () -> Unit
) {

    val items = mutableListOf(
        BottomNavItem.FavouritesItem,
        BottomNavItem.AnimeListItem,
        BottomNavItem.ProfileItem,
    )
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true,
                        onClick = {
                            navController.navigate(item.screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.imageVector,
                                contentDescription = item.title,
                            )
                        },
                        label = { Text(text = item.title) }
                    )
                }
            }
        }
    ) {
        MainNavGraph(
            navController,
            logout
        )
    }
}

sealed class BottomNavItem(val imageVector: ImageVector, val title: String, val screen: AppScreen) {
    data object FavouritesItem : BottomNavItem(imageVector = Icons.Default.Favorite, title = "Favourites", AppScreen.FavouritesScreen)
    data object AnimeListItem : BottomNavItem(imageVector = Icons.AutoMirrored.Filled.List, title = "Favourites", AppScreen.AnimeListScreen)
    data object ProfileItem : BottomNavItem(imageVector = Icons.Default.Person, title = "Favourites", AppScreen.ProfileScreen)
}