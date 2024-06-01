package com.compose.filerush.navigation

sealed class FilerushRoutes(
    val route: String
) {
    data object Home: FilerushRoutes("home")

    data object DownloadDetail: FilerushRoutes("downloadDetails")

    companion object {
        const val DOWNLOAD_ID_KEY = "downloadId"
    }
}