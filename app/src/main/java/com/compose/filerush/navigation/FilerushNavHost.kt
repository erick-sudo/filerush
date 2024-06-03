package com.compose.filerush.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.filerush.model.DownloadConfig
import com.compose.filerush.ui.home.Home

@Composable
fun FilerushNavHost(
    allDownloads: List<DownloadConfig>,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = FilerushRoutes.Home.route
    ) {

        composable(
            route = FilerushRoutes.Home.route
        ) {
            Home(
                allDownloads = allDownloads
            )
        }
    }
}