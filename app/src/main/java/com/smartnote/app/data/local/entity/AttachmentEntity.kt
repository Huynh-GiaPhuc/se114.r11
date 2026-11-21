package com.smartnote.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.smartnote.app.domain.model.Attachment
import com.smartnote.app.domain.model.AttachmentType

@Entity(
    tableName = "note_attachments",
    foreignKeys = [
        ForeignKey(
            entity = NoteEntity::class,
            parentColumns = ["id"],
            childColumns = ["noteId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["noteId"])]
)
data class AttachmentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val noteId: Long,
    val filePath: String,
    val fileType: String,
    val createdAt: Long
)

fun AttachmentEntity.toDomainModel(): Attachment {
    return Attachment(
        id = id,
        noteId = noteId,
        filePath = filePath,
        fileType = try {
            AttachmentType.valueOf(fileType)
        } catch (e: Exception) {
            AttachmentType.FILE
        },
        createdAt = createdAt
    )
}

fun Attachment.toEntity(): AttachmentEntity {
    return AttachmentEntity(
        id = id,
        noteId = noteId,
        filePath = filePath,
        fileType = fileType.name,
        createdAt = createdAt
    )
}
