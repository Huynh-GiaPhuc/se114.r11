package com.smartnote.app.domain.model

data class Note(
    val id: Long = 0,
    val title: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val color: Int = 0xFFFFF59D.toInt(),
    val isPinned: Boolean = false,
    val isFavorite: Boolean = false,
    val isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    val tags: List<String> = emptyList(),
    val folderId: Long? = null
)
