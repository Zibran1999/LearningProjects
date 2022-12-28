package com.zibran.learningprojects.wallpaper_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zibran.learningprojects.R
import com.zibran.learningprojects.databinding.WallItemLayoutBinding
import com.zibran.learningprojects.wallpaper_app.model.Hit
import javax.inject.Inject

class WallpaperAdapter @Inject constructor() : RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {
    val list = mutableListOf<Hit>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: WallItemLayoutBinding = WallItemLayoutBinding.bind(itemView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.wall_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView.context).load(list[position].largeImageURL)
            .into(holder.binding.img)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: MutableList<Hit>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

}