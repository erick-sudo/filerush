package com.compose.filerush.reposiroty

import androidx.lifecycle.LiveData
import com.compose.filerush.model.DownloadConfig
import com.compose.filerush.model.DownloadsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DownloadsRepository(
    private val downloadsDao: DownloadsDao
) {

    val allDownloads: LiveData<List<DownloadConfig>> = downloadsDao.getAllDownloads()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertDownloads(vararg downloadConfig: DownloadConfig) {
        coroutineScope.launch {
            downloadsDao.insertDownloads(*downloadConfig)
        }
    }


}