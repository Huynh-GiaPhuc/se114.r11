package com.smartnote.app.domain.model

data class Category(
    val id: Long = 0,
    val name: String,
    val colorHex: String = "#6650a4",
    val iconName: String = "ic_folder"
)
