package com.zibran.learningprojects.wallpaper_app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zibran.learningprojects.wallpaper_app.model.Hit

@Dao
interface WallpaperDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWallpaper(wallList: List<Hit>)

    @Query("SELECT * FROM wallpapers")
    suspend fun getWallpapers(): List<Hit>
}