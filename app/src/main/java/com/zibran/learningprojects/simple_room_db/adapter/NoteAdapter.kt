package com.zibran.learningprojects.simple_room_db.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zibran.learningprojects.R
import com.zibran.learningprojects.simple_room_db.model.NoteModel

class NoteAdapter(
    private val context: Context,
    private val list: MutableList<NoteModel>,
    private val clickListener: ClickListener,
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = list[position].notesTitle
        holder.itemView.setOnClickListener {
            clickListener.onClick(list[position])
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface ClickListener {
    fun onClick(noteModel: NoteModel)
}