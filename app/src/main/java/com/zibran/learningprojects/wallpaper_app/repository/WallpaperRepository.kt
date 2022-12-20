package com.zibran.learningprojects.wallpaper_app.repository

import com.zibran.learningprojects.wallpaper_app.model.Hit
import com.zibran.learningprojects.wallpaper_app.model.WallpaperModel
import com.zibran.learningprojects.wallpaper_app.services.ApiService
import retrofit2.Response
import javax.inject.Inject

class WallpaperRepository @Inject constructor(private val apiService: ApiService) : WallRepo {
    lateinit var response: Response<WallpaperModel>


    override suspend fun getWallpaperDetails(query: String): List<Hit> {
        response =
            apiService.getWallpaper("https://pixabay.com/api/?key=32178486-df3a375645a9a74666e8fca3e&q={$query}&image_type=photo&per_page=200")
        return response
            .body()!!.hits
    }
}