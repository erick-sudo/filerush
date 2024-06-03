package com.compose.filerush.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URL
import java.util.UUID
import kotlin.math.ceil
import kotlin.math.min

@Entity(tableName = "downloads")
class DownloadConfig (
    @PrimaryKey
    @ColumnInfo(name = "downloadId")
    var downloadId:  String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "url")
    var url: URL,

    @ColumnInfo(name = "fileName")
    var fileName: String,

    @ColumnInfo(name = "mimeType")
    var mimeType: String,

    @ColumnInfo(name = "downloadState")
    var downloadState: DownloadState = DownloadState.INITIALIZING
) {

    companion object {
        fun init(url: URL, fileName: String, contentLength: Long): DownloadConfig {
            val numberOfConcurrentThreads = Runtime.getRuntime().availableProcessors()
            val maximumRangeSize = contentLength/numberOfConcurrentThreads

            val downloadConfig = DownloadConfig(
                downloadId = UUID.randomUUID().toString(),
                url = url,
                fileName = fileName,
                mimeType = "image/jpg",
            )

            val downloadSections = (0..<ceil(contentLength.toDouble()/maximumRangeSize).toLong()).map {
                val offset = it * maximumRangeSize
                val end = min(offset + maximumRangeSize - 1, contentLength - 1)
                DownloadSection(
                    uuid = UUID.randomUUID().toString(),
                    receivedBytes = 0,
                    totalBytes = end - offset + 1,
                    offset = offset,
                    end = end,
                    downloadId = downloadConfig.downloadId
                )
            }
            return downloadConfig
        }
    }
}