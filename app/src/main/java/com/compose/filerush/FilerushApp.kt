package com.compose.filerush

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.filerush.components.FilerushBottomBar
import com.compose.filerush.components.FilerushDrawer
import com.compose.filerush.components.FilerushScaffold
import com.compose.filerush.components.FilerushTabs
import com.compose.filerush.navigation.FilerushNavHost
import com.compose.filerush.navigation.rememberFilerushNavController
import com.compose.filerush.ui.theme.FilerushTheme
import com.compose.filerush.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun FilerushApp(
    // The main view model
    mainViewModel: MainViewModel = viewModel()
) {

    // Navigation controller
    val filerushNavController = rememberFilerushNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val drawerOpen by mainViewModel.drawerOpened.collectAsState()

    if(drawerOpen) {
        // Open drawer and reset the state in the view model
        LaunchedEffect(Unit) {
            // Handling any possible CancellationException while opening the drawer
            try {
                drawerState.open()
            } finally {
                mainViewModel.resetDrawer()
            }
        }
    }

    // Intercept back navigation when drawer is open
    val scope = rememberCoroutineScope()
    if(drawerState.isOpen) {
        BackHandler {
            scope.launch {
                drawerState.close()
            }
        }
    }

    // App theme
    FilerushTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Encapsulation of a modal navigation drawer
            FilerushDrawer(
                drawerState = drawerState,
            ) {
                // Scaffolding provider
                FilerushScaffold(
                    bottomBar = {
                        FilerushBottomBar(
                            tabs = FilerushTabs.entries.toTypedArray(),
                            currentRoute = filerushNavController.currentRoute?: "home",
                            navigateToRoute = {  },
                        )
                    }
                ) { paddingValues ->
                    // Default navigation host
                    FilerushNavHost(
                        modifier = Modifier.padding(paddingValues),
                        navController = filerushNavController.navController
                    )
                }
            }
        }
    }
}