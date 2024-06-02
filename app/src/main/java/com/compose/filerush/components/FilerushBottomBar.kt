package com.compose.filerush.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AreaChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

import com.compose.filerush.R

enum class FilerushTabs(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    HOME(R.string.tab_home, Icons.Outlined.Home, "home"),
    HISTORY(R.string.tab_history, Icons.Outlined.Home, "history"),
    NETWORK(R.string.tab_network, Icons.Outlined.AreaChart, "network"),
    SETTINGS(R.string.tab_settings, Icons.Outlined.Settings, "settings")
}

@Composable
fun FilerushBottomBar(
    tabs: Array<FilerushTabs>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {

}