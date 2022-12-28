package com.zibran.learningprojects.main.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.zibran.learningprojects.databinding.ActivityMainBinding
import com.zibran.learningprojects.main.adapter.ClickListener
import com.zibran.learningprojects.main.adapter.MainAdapter
import com.zibran.learningprojects.music_app.activities.LoginActivity
import com.zibran.learningprojects.simple_room_db.activity.NoteAppActivity
import com.zibran.learningprojects.wallpaper_app.activities.HomeActivity

class MainActivity : AppCompatActivity(), ClickListener {
    lateinit var binding: ActivityMainBinding
    var list = mutableListOf<String>()
    lateinit var adapter: MainAdapter
    var pos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        list.add("Notes App With Room DB")
        list.add("Wallpaper App")
        list.add("Music App")

        adapter = MainAdapter(this, list, this)
        binding.projectNameRV.adapter = adapter

    }

    override fun onClick(title: String) {
        when (title) {
            "Notes App With Room DB" -> {
                startActivity(Intent(this, NoteAppActivity::class.java))
            }
            "Music App" -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            "Wallpaper App" -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }
            else -> {
                Snackbar.make(binding.constraintLayout2,
                    "Sorry, It's not a right choice.",
                    Snackbar.LENGTH_LONG).show()
            }
        }
    }
}