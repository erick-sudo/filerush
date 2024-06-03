package com.compose.filerush.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.compose.filerush.model.DownloadConfig
import com.compose.filerush.model.DownloadsRoomDatabase
import com.compose.filerush.reposiroty.DownloadsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(application: Application): ViewModel() {

    val allDownloads: LiveData<List<DownloadConfig>>

    private val downloadsRepository: DownloadsRepository

    private val _drawerOpened = MutableStateFlow(false)
    val drawerOpened = _drawerOpened.asStateFlow()

    init {
        // Obtaining an instance of RoomDatabase
        val downloadsDatabase = DownloadsRoomDatabase.getInstance(application)
        // Obtaining an instance of a data access object
        val downloadsDao = downloadsDatabase.downloadsDao()

        // Initialize the Downloads Repository
        downloadsRepository = DownloadsRepository(downloadsDao)

        // Fetch all the downloads
        allDownloads = downloadsRepository.allDownloads
    }

    fun openDrawer() {
        _drawerOpened.value = true
    }

    fun resetDrawer() {
        _drawerOpened.value = false
    }
}

/**
 * Factory class for initializing an instance of MainViewModel
 */
class MainViewModelFactory(
    val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(application) as T
    }
}