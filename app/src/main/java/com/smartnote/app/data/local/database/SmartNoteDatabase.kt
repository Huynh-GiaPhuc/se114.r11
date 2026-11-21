package com.smartnote.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smartnote.app.data.local.dao.AttachmentDao
import com.smartnote.app.data.local.dao.CategoryDao
import com.smartnote.app.data.local.dao.NoteDao
import com.smartnote.app.data.local.entity.AttachmentEntity
import com.smartnote.app.data.local.entity.CategoryEntity
import com.smartnote.app.data.local.entity.NoteCategoryCrossRef
import com.smartnote.app.data.local.entity.NoteEntity

@Database(
    entities = [
        NoteEntity::class,
        CategoryEntity::class,
        NoteCategoryCrossRef::class,
        AttachmentEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class SmartNoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
    abstract val categoryDao: CategoryDao
    abstract val attachmentDao: AttachmentDao

    companion object {
        const val DATABASE_NAME = "smart_note_db"
    }
}
