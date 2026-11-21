package com.smartnote.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smartnote.app.data.local.dao.NoteDao
import com.smartnote.app.data.local.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SmartNoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "smart_note_db"
    }
}
