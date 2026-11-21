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
    val timestamp: Long,
    val color: Int,
    val isPinned: Boolean,
    val isFavorite: Boolean,
    val isArchived: Boolean,
    val isDeleted: Boolean,
    val tags: String,
    val folderId: Long?
)

fun NoteEntity.toDomainModel(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        timestamp = timestamp,
        color = color,
        isPinned = isPinned,
        isFavorite = isFavorite,
        isArchived = isArchived,
        isDeleted = isDeleted,
        tags = if (tags.isEmpty()) emptyList() else tags.split(","),
        folderId = folderId
    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        timestamp = timestamp,
        color = color,
        isPinned = isPinned,
        isFavorite = isFavorite,
        isArchived = isArchived,
        isDeleted = isDeleted,
        tags = tags.joinToString(","),
        folderId = folderId
    )
}
