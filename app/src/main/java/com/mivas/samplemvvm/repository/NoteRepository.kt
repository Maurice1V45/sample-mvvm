package com.mivas.samplemvvm.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mivas.samplemvvm.database.NoteDatabase
import com.mivas.samplemvvm.database.dao.NoteDao
import com.mivas.samplemvvm.database.model.Note
import org.jetbrains.anko.doAsync

class NoteRepository(application: Application) {

    private var noteDao: NoteDao
    private var allNotes: LiveData<List<Note>>

    init {
        val database: NoteDatabase = NoteDatabase.getInstance(application.applicationContext)!!
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) = doAsync { noteDao.insert(note) }
    fun update(note: Note) = doAsync { noteDao.update(note) }
    fun delete(note: Note) = doAsync { noteDao.delete(note) }
    fun deleteAllNotes() = doAsync { noteDao.deleteAllNotes() }
    fun getAllNotes() = allNotes
}