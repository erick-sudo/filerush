package com.compose.filerush.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DownloadsDao {

    @Query("SELECT * FROM downloads")
    fun getAllDownloads(): LiveData<List<DownloadConfig>>

    @Query("SELECT * FROM downloads WHERE downloadId = :downloadId")
    fun findDownloadById(downloadId: String): DownloadConfig?

    @Insert
    fun insertDownloads(vararg downloadConfig: DownloadConfig)

    @Update
    fun updateDownload(downloadConfig: DownloadConfig)

    @Delete
    fun deleteDownload(vararg downloadConfig: DownloadConfig)

    @Insert
    fun insertDownloadSections(vararg downloadSection: DownloadSection)

    @Query("SELECT * FROM download_sections WHERE downloadId = :downloadId")
    fun findDownloadSections(downloadId: String): List<DownloadSection>
}