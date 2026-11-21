package com.smartnote.app.domain.repository

import com.smartnote.app.domain.model.Attachment
import com.smartnote.app.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllActiveNotes(): Flow<List<Note>>
    fun getNoteById(id: Long): Flow<Note?>
    fun getPinnedNotes(): Flow<List<Note>>
    fun getTrashedNotes(): Flow<List<Note>>
    fun searchNotes(query: String): Flow<List<Note>>
    fun getNotesByCategory(categoryId: Long): Flow<List<Note>>
    
    suspend fun insertNote(note: Note, categoryIds: List<Long> = emptyList()): Long
    suspend fun updateNote(note: Note, categoryIds: List<Long> = emptyList())
    suspend fun moveNoteToTrash(id: Long)
    suspend fun restoreNote(id: Long)
    suspend fun deleteNotePermanently(id: Long)
    suspend fun emptyTrash()
    
    suspend fun addAttachmentToNote(attachment: Attachment): Long
    suspend fun deleteAttachment(attachment: Attachment)
    fun getAttachmentsForNote(noteId: Long): Flow<List<Attachment>>
}
