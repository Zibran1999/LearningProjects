package com.zibran.learningprojects.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zibran.learningprojects.R

class MainAdapter(
    val context: Context,
    val list: MutableList<String>,
    val clickListener: ClickListener,
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = list[position]
        holder.itemView.setOnClickListener {
            clickListener.onClick(list[position])
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface ClickListener {
    fun onClick(title: String)
}