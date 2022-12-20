package com.zibran.learningprojects.simple_room_db.db

import androidx.room.*
import com.zibran.learningprojects.simple_room_db.model.NoteModel

@Dao
interface NoteDao {
    @Insert
    fun insert(noteModel: NoteModel)

    @Query("SELECT * FROM NoteModel")
    fun getAll(): MutableList<NoteModel>

    @Delete
    fun deleteNote(noteModel: NoteModel)

    @Update
    fun updateNote(noteModel: NoteModel)
}