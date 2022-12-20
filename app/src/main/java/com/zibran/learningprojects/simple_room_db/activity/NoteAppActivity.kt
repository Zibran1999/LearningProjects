package com.zibran.learningprojects.simple_room_db.activity

import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.zibran.learningprojects.databinding.ActivityNoteAppBinding
import com.zibran.learningprojects.databinding.AddNotesBinding
import com.zibran.learningprojects.simple_room_db.adapter.ClickListener
import com.zibran.learningprojects.simple_room_db.adapter.NoteAdapter
import com.zibran.learningprojects.simple_room_db.db.AppDatabase
import com.zibran.learningprojects.simple_room_db.model.NoteModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteAppActivity : AppCompatActivity(), ClickListener {
    lateinit var appDatabase: AppDatabase
    lateinit var noteAdapter: NoteAdapter
    lateinit var list: MutableList<NoteModel>
    lateinit var binding: ActivityNoteAppBinding
    lateinit var addNotesBinding: AddNotesBinding
    lateinit var dialog: Dialog
    lateinit var alertDialog: MaterialAlertDialogBuilder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Notes App"

        appDatabase = AppDatabase.getInstance(this)
        list = mutableListOf()

        getNotes()

        binding.addNoteBtn.setOnClickListener {
            addNoteDialog()
        }


    }

    private fun addNoteDialog() {
        dialog = Dialog(this)
        addNotesBinding = AddNotesBinding.inflate(layoutInflater)
        dialog.setContentView(addNotesBinding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        addNotesBinding.addNoteBtn.setOnClickListener {
            if (addNotesBinding.note.text.isNullOrEmpty()) {
                addNotesBinding.note.error = "Please Add your Note!"
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    appDatabase.getNoteDao()
                        .insert(NoteModel(0, addNotesBinding.note.text.toString()))
                    dialog.dismiss()
                    CoroutineScope(Dispatchers.Main).launch {
                        Snackbar.make(binding.container,
                            "Note Added successfully.",
                            Snackbar.LENGTH_LONG).show()

                    }
                    getNotes()
                }
            }
        }

    }

    private fun getNotes() {
        CoroutineScope(Dispatchers.IO).launch {
            list = appDatabase.getNoteDao().getAll()

            CoroutineScope(Dispatchers.Main).launch {
                noteAdapter = NoteAdapter(this@NoteAppActivity, list, this@NoteAppActivity)
                binding.notesRV.adapter = noteAdapter
                noteAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClick(noteModel: NoteModel) {
        alertDialog = MaterialAlertDialogBuilder(this)
        alertDialog.setTitle("UpdateNote")
        alertDialog.setMessage("Do you really want to delete this note?")
        alertDialog.setNegativeButton("CANCEL", object : OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {

            }
        }).setPositiveButton("Yes, DELETE", object : OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                CoroutineScope(Dispatchers.IO).launch {
                    appDatabase.getNoteDao().deleteNote(noteModel)
                    getNotes()
                }
            }

        }).setNeutralButton("UPDATE", object : OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                updateNoteDialog(noteModel)
            }

        }).show()

    }

    private fun updateNoteDialog(noteModel: NoteModel) {
        dialog = Dialog(this)
        addNotesBinding = AddNotesBinding.inflate(layoutInflater)
        dialog.setContentView(addNotesBinding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
        addNotesBinding.note.setText(noteModel.notesTitle)

        addNotesBinding.addNoteBtn.setOnClickListener {
            if (addNotesBinding.note.text.isNullOrEmpty()) {
                addNotesBinding.note.error = "Please Add your Note!"
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    appDatabase.getNoteDao()
                        .updateNote(NoteModel(noteModel.id, addNotesBinding.note.text.toString()))
                    dialog.dismiss()
                    CoroutineScope(Dispatchers.Main).launch {
                        Snackbar.make(binding.container,
                            "Note Updated successfully.",
                            Snackbar.LENGTH_LONG).show()

                    }
                    getNotes()
                }
            }
        }

    }


}