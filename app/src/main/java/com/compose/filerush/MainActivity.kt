package com.compose.filerush

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.filerush.viewmodels.MainViewModel
import com.compose.filerush.viewmodels.MainViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val owner = LocalViewModelStoreOwner.current
            owner?.let { storeOwner ->
                val mainViewModel: MainViewModel =
                    viewModel(
                        storeOwner,
                        "MainViewModel",
                        MainViewModelFactory(
                            LocalContext.current.applicationContext as Application
                        )
                    )

                FilerushApp(mainViewModel)
            }


        }
    }
}