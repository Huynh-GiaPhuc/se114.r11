package com.smartnote.app.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.smartnote.app.domain.model.Note

data class NoteWithDetails(
    @Embedded val note: NoteEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = NoteCategoryCrossRef::class,
            parentColumn = "noteId",
            entityColumn = "categoryId"
        )
    )
    val categories: List<CategoryEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "noteId"
    )
    val attachments: List<AttachmentEntity>
)

fun NoteWithDetails.toDomainModel(): Note {
    return Note(
        id = note.id,
        title = note.title,
        content = note.content,
        colorHex = note.colorHex,
        isPinned = note.isPinned,
        isLocked = note.isLocked,
        isTrashed = note.isTrashed,
        trashedAt = note.trashedAt,
        createdAt = note.createdAt,
        updatedAt = note.updatedAt,
        categories = categories.map { it.toDomainModel() },
        attachments = attachments.map { it.toDomainModel() }
    )
}
