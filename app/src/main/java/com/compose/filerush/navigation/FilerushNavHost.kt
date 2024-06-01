package com.compose.filerush.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost

@Composable
fun FilerushNavHost(

) {

    val filerushNavController = rememberFilerushNavController()

    NavHost(
        navController = filerushNavController.navController,
        startDestination = FilerushRoutes.Home.route
    ) {

    }
}