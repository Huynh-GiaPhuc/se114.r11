package com.smartnote.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smartnote.app.domain.model.Category

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val colorHex: String,
    val iconName: String
)

fun CategoryEntity.toDomainModel(): Category {
    return Category(
        id = id,
        name = name,
        colorHex = colorHex,
        iconName = iconName
    )
}

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        colorHex = colorHex,
        iconName = iconName
    )
}
