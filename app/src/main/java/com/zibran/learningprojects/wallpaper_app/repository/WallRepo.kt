package com.zibran.learningprojects.wallpaper_app.repository

import com.zibran.learningprojects.wallpaper_app.model.Hit

interface WallRepo {
    suspend fun getWallpaperDetails(query: String): List<Hit>
}