package com.compose.filerush.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "download_sections",
    foreignKeys = [ForeignKey(
        entity = DownloadConfig::class,
        parentColumns = arrayOf("downloadId"),
        childColumns = arrayOf("downloadId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class DownloadSection(
    @PrimaryKey
    @ColumnInfo(name = "uuid")
    var uuid: String,

    @ColumnInfo(name = "receivedBytes")
    var receivedBytes: Long,

    @ColumnInfo(name = "totalBytes")
    var totalBytes: Long,

    @ColumnInfo(name = "offset")
    var offset: Long,

    @ColumnInfo(name = "end")
    var end: Long,

    @ColumnInfo(name = "downloadId")
    var downloadId: String,
)