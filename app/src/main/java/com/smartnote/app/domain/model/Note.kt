package com.smartnote.app.domain.model

data class Note(
    val id: Long = 0,
    val title: String,
    val content: String,
    val colorHex: String = "#FFF59D",
    val isPinned: Boolean = false,
    val isLocked: Boolean = false,
    val isTrashed: Boolean = false,
    val trashedAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val categories: List<Category> = emptyList(),
    val attachments: List<Attachment> = emptyList()
)
