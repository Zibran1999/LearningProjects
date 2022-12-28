package com.zibran.learningprojects.wallpaper_app.repository

import android.content.Context
import com.zibran.learningprojects.main.activity.MainActivity
import com.zibran.learningprojects.wallpaper_app.db.WallpaperDao
import com.zibran.learningprojects.wallpaper_app.model.Hit
import com.zibran.learningprojects.wallpaper_app.model.WallpaperModel
import com.zibran.learningprojects.wallpaper_app.services.ApiService
import com.zibran.learningprojects.wallpaper_app.utils.Utils
import retrofit2.Response
import javax.inject.Inject

class WallpaperRepository @Inject constructor(
    private val apiService: ApiService,
    private val wallpaperDao: WallpaperDao,
    private val context: Context,
) : WallRepo {

    lateinit var response: Response<WallpaperModel>
    lateinit var list: List<Hit>


    override suspend fun getWallpaperDetails(query: String): List<Hit> {
        if (Utils.isInternetConnected(context)) {
            response =
                apiService.getWallpaper("https://pixabay.com/api/?key=32178486-df3a375645a9a74666e8fca3e&q={$query}&image_type=photo&per_page=200")
            wallpaperDao.insertWallpaper(response.body()!!.hits)
            list = response.body()!!.hits
        } else {
            list = wallpaperDao.getWallpapers()
        }
        return list
    }
}