package com.smartnote.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.smartnote.app.data.local.entity.NoteEntity
import com.smartnote.app.data.local.entity.NoteWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Transaction
    @Query("SELECT * FROM notes WHERE isTrashed = 0 ORDER BY isPinned DESC, updatedAt DESC")
    fun getAllActiveNotesWithDetails(): Flow<List<NoteWithDetails>>

    @Transaction
    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteByIdWithDetails(id: Long): Flow<NoteWithDetails?>

    @Transaction
    @Query("SELECT * FROM notes WHERE isPinned = 1 AND isTrashed = 0 ORDER BY updatedAt DESC")
    fun getPinnedNotesWithDetails(): Flow<List<NoteWithDetails>>

    @Transaction
    @Query("SELECT * FROM notes WHERE isTrashed = 1 ORDER BY trashedAt DESC")
    fun getTrashedNotesWithDetails(): Flow<List<NoteWithDetails>>

    @Transaction
    @Query("SELECT * FROM notes WHERE isTrashed = 0 AND (title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%') ORDER BY updatedAt DESC")
    fun searchNotesWithDetails(query: String): Flow<List<NoteWithDetails>>

    @Transaction
    @Query("""
        SELECT notes.* FROM notes 
        INNER JOIN note_category_cross_ref ON notes.id = note_category_cross_ref.noteId 
        WHERE note_category_cross_ref.categoryId = :categoryId AND notes.isTrashed = 0 
        ORDER BY notes.isPinned DESC, notes.updatedAt DESC
    """)
    fun getNotesByCategoryWithDetails(categoryId: Long): Flow<List<NoteWithDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("UPDATE notes SET isTrashed = 1, trashedAt = :trashedAt WHERE id = :id")
    suspend fun moveNoteToTrash(id: Long, trashedAt: Long = System.currentTimeMillis())

    @Query("UPDATE notes SET isTrashed = 0, trashedAt = NULL WHERE id = :id")
    suspend fun restoreNoteFromTrash(id: Long)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNotePermanently(id: Long)

    @Query("DELETE FROM notes WHERE isTrashed = 1")
    suspend fun emptyTrash()
}
