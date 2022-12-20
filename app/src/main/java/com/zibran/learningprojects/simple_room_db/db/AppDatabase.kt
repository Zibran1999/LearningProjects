package com.zibran.learningprojects.simple_room_db.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zibran.learningprojects.simple_room_db.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, "notes_app.db").build()
            }
            return instance!!
        }
    }
}