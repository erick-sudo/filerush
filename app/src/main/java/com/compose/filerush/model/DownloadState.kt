package com.compose.filerush.model

enum class DownloadState(
    val status: String
) {
    INITIALIZING("Initializing"),
    DOWNLOADING("Downloading"),
    PAUSED("Paused"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed")
}