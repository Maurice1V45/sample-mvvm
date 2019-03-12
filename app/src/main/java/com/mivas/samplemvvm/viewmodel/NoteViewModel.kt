package com.mivas.samplemvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mivas.samplemvvm.repository.NoteRepository
import com.mivas.samplemvvm.database.model.Note

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = NoteRepository(application)
    private var allNotes = repository.getAllNotes()

    fun insert(note: Note) = repository.insert(note)
    fun update(note: Note) = repository.update(note)
    fun delete(note: Note) = repository.delete(note)
    fun deleteAllNotes() = repository.deleteAllNotes()
    fun getAllNotes() = allNotes
}