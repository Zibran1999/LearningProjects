package com.zibran.learningprojects.wallpaper_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zibran.learningprojects.wallpaper_app.model.Hit
import com.zibran.learningprojects.wallpaper_app.repository.WallpaperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val wallpaperRepository: WallpaperRepository,
) :
    ViewModel() {

    suspend fun getWallPaper(query: String): List<Hit> {
        return wallpaperRepository.getWallpaperDetails(query)
    }


}