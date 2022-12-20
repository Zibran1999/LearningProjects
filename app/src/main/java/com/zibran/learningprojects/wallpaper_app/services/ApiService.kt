package com.zibran.learningprojects.wallpaper_app.services

import com.zibran.learningprojects.wallpaper_app.model.WallpaperModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getWallpaper(@Url query: String): Response<WallpaperModel>
}