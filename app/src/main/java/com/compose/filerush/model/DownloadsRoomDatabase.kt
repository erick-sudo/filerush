package com.compose.filerush.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.net.URL

@Database(entities = [(DownloadConfig::class), (DownloadSection::class)], version = 1)
@TypeConverters(Converters::class)
abstract class DownloadsRoomDatabase: RoomDatabase() {

    abstract fun downloadsDao(): DownloadsDao

    companion object {
        private var INSTANCE: DownloadsRoomDatabase? = null

        fun getInstance(context: Context): DownloadsRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DownloadsRoomDatabase::class.java,
                        "downloads_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

class Converters {
    @TypeConverter
    fun fromURl(url: URL): String {
        return url.toString()
    }

    @TypeConverter
    fun toUrl(urlString: String): URL {
        return  URL(urlString)
    }

    @TypeConverter
    fun fromDownloadState(state: DownloadState): String {
        return state.name
    }

    @TypeConverter
    fun toDownloadState(stateString: String): DownloadState {
        return DownloadState.valueOf(stateString)
    }
}