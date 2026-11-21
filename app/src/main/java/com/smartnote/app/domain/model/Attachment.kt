package com.smartnote.app.domain.model

enum class AttachmentType {
    IMAGE, AUDIO, FILE
}

data class Attachment(
    val id: Long = 0,
    val noteId: Long,
    val filePath: String,
    val fileType: AttachmentType,
    val createdAt: Long = System.currentTimeMillis()
)
