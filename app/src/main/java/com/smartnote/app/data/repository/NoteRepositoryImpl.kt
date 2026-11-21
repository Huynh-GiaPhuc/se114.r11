package com.smartnote.app.data.repository

import com.smartnote.app.data.local.dao.NoteDao
import com.smartnote.app.data.local.entity.toDomainModel
import com.smartnote.app.data.local.entity.toEntity
import com.smartnote.app.domain.model.Note
import com.smartnote.app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllActiveNotes().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun getNoteById(id: Long): Flow<Note?> {
        return noteDao.getNoteById(id).map { it?.toDomainModel() }
    }

    override fun getFavoriteNotes(): Flow<List<Note>> {
        return noteDao.getFavoriteNotes().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun getDeletedNotes(): Flow<List<Note>> {
        return noteDao.getDeletedNotes().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun searchNotes(query: String): Flow<List<Note>> {
        return noteDao.searchNotes(query).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun insertNote(note: Note): Long {
        return noteDao.insertNote(note.toEntity())
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.updateNote(note.copy(isDeleted = true).toEntity())
    }

    override suspend fun restoreNote(id: Long) {
        noteDao.restoreNote(id)
    }

    override suspend fun deleteNotePermanently(id: Long) {
        noteDao.deleteNotePermanently(id)
    }
}
