package com.compose.filerush

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.view.ViewCompat
import com.compose.filerush.components.FilerushDrawer
import com.compose.filerush.databinding.FragmentHomeBinding
import com.compose.filerush.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    // The main view model
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, insets -> insets }

        setContentView(
            ComposeView(this).apply {
                consumeWindowInsets = false

                setContent {
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

                    FilerushDrawer(
                        drawerState = drawerState
                    ) {
                        AndroidViewBinding(FragmentHomeBinding::inflate)
                    }
                }
            }
        )
    }
}