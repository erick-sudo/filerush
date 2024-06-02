package com.compose.filerush.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * Create and remember an instance of [FilerushNavController]
 */
@Composable
fun rememberFilerushNavController(
    navController: NavHostController = rememberNavController()
): FilerushNavController = remember {
    FilerushNavController(navController)
}

/**
 * Holds navigation logic
 */
@Stable
class FilerushNavController(
    val navController: NavHostController
) {
    // Navigation source of truth
    val currentRoute: String? by mutableStateOf(navController.currentDestination?.route)

    fun navigateTo(route: String) {
        if(route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true

                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }
}

/**
 * Check if the current lifecycle in the backstack entry is resumed
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

/**
 * Extension function to find the start navigation destination
 * in the navigation graph
 */
private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

/**
 * Recursively find the start destination
 */
private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if(graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}