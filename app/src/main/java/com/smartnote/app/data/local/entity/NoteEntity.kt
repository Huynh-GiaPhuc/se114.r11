package com.smartnote.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smartnote.app.domain.model.Note

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    val colorHex: String,
    val isPinned: Boolean,
    val isLocked: Boolean,
    val isTrashed: Boolean,
    val trashedAt: Long?,
    val createdAt: Long,
    val updatedAt: Long
)

fun NoteEntity.toDomainModel(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        colorHex = colorHex,
        isPinned = isPinned,
        isLocked = isLocked,
        isTrashed = isTrashed,
        trashedAt = trashedAt,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        colorHex = colorHex,
        isPinned = isPinned,
        isLocked = isLocked,
        isTrashed = isTrashed,
        trashedAt = trashedAt,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
