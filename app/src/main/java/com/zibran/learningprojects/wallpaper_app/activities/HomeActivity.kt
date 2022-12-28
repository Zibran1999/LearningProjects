package com.zibran.learningprojects.wallpaper_app.activities

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zibran.learningprojects.databinding.ActivityHomeBinding
import com.zibran.learningprojects.wallpaper_app.adapter.WallpaperAdapter
import com.zibran.learningprojects.wallpaper_app.model.Hit
import com.zibran.learningprojects.wallpaper_app.viewModel.WallpaperViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val wallpaperViewModel by viewModels<WallpaperViewModel>()
    var list = mutableListOf<Hit>()

    @Inject
    lateinit var wallpaperAdapter: WallpaperAdapter
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        wallpaperAdapter = WallpaperAdapter(this)
        binding.wallRV.adapter = wallpaperAdapter
        setWallpaperData("flower")

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                setWallpaperData(p0!!)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

    }

    private fun setWallpaperData(query: String) {
        list.clear()
        CoroutineScope(Dispatchers.IO).launch {
            list.addAll(wallpaperViewModel.getWallPaper(query.trim()))
            CoroutineScope(Dispatchers.Main).launch {
                wallpaperAdapter.updateList(list)
            }
        }
    }

}

