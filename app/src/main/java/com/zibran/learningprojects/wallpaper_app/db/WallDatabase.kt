package com.zibran.learningprojects.wallpaper_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zibran.learningprojects.wallpaper_app.model.Hit

@Database(entities = [Hit::class], version = 1)
abstract class WallDatabase : RoomDatabase() {

    abstract fun getWallDao(): WallpaperDao

    companion object {

        var instance: WallDatabase? = null

        fun getInstance(context: Context): WallDatabase {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    WallDatabase::class.java,
                    "wallpaper_db").build()
            }
            return instance!!
        }
    }
}

