package com.smartnote.app.domain.repository

import com.smartnote.app.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    fun getNoteById(id: Long): Flow<Note?>
    fun getFavoriteNotes(): Flow<List<Note>>
    fun getDeletedNotes(): Flow<List<Note>>
    fun searchNotes(query: String): Flow<List<Note>>
    suspend fun insertNote(note: Note): Long
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun restoreNote(id: Long)
    suspend fun deleteNotePermanently(id: Long)
}
