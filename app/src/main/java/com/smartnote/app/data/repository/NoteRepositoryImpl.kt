package com.smartnote.app.data.repository

import com.smartnote.app.data.local.dao.AttachmentDao
import com.smartnote.app.data.local.dao.CategoryDao
import com.smartnote.app.data.local.dao.NoteDao
import com.smartnote.app.data.local.entity.NoteCategoryCrossRef
import com.smartnote.app.data.local.entity.toDomainModel
import com.smartnote.app.data.local.entity.toEntity
import com.smartnote.app.domain.model.Attachment
import com.smartnote.app.domain.model.Note
import com.smartnote.app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val categoryDao: CategoryDao,
    private val attachmentDao: AttachmentDao
) : NoteRepository {

    override fun getAllActiveNotes(): Flow<List<Note>> {
        return noteDao.getAllActiveNotesWithDetails().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun getNoteById(id: Long): Flow<Note?> {
        return noteDao.getNoteByIdWithDetails(id).map { it?.toDomainModel() }
    }

    override fun getPinnedNotes(): Flow<List<Note>> {
        return noteDao.getPinnedNotesWithDetails().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun getTrashedNotes(): Flow<List<Note>> {
        return noteDao.getTrashedNotesWithDetails().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun searchNotes(query: String): Flow<List<Note>> {
        return noteDao.searchNotesWithDetails(query).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun getNotesByCategory(categoryId: Long): Flow<List<Note>> {
        return noteDao.getNotesByCategoryWithDetails(categoryId).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun insertNote(note: Note, categoryIds: List<Long>): Long {
        val noteId = noteDao.insertNote(note.toEntity())
        if (categoryIds.isNotEmpty()) {
            categoryDao.clearCategoriesForNote(noteId)
            categoryIds.forEach { categoryId ->
                categoryDao.insertNoteCategoryCrossRef(
                    NoteCategoryCrossRef(noteId = noteId, categoryId = categoryId)
                )
            }
        }
        return noteId
    }

    override suspend fun updateNote(note: Note, categoryIds: List<Long>) {
        noteDao.updateNote(note.toEntity())
        categoryDao.clearCategoriesForNote(note.id)
        categoryIds.forEach { categoryId ->
            categoryDao.insertNoteCategoryCrossRef(
                NoteCategoryCrossRef(noteId = note.id, categoryId = categoryId)
            )
        }
    }

    override suspend fun moveNoteToTrash(id: Long) {
        noteDao.moveNoteToTrash(id)
    }

    override suspend fun restoreNote(id: Long) {
        noteDao.restoreNoteFromTrash(id)
    }

    override suspend fun deleteNotePermanently(id: Long) {
        noteDao.deleteNotePermanently(id)
    }

    override suspend fun emptyTrash() {
        noteDao.emptyTrash()
    }

    override suspend fun addAttachmentToNote(attachment: Attachment): Long {
        return attachmentDao.insertAttachment(attachment.toEntity())
    }

    override suspend fun deleteAttachment(attachment: Attachment) {
        attachmentDao.deleteAttachment(attachment.toEntity())
    }

    override fun getAttachmentsForNote(noteId: Long): Flow<List<Attachment>> {
        return attachmentDao.getAttachmentsForNote(noteId).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }
}
