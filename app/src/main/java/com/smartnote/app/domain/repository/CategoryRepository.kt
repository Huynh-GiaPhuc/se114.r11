package com.smartnote.app.domain.repository

import com.smartnote.app.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<Category>>
    fun getCategoryById(id: Long): Flow<Category?>
    suspend fun insertCategory(category: Category): Long
    suspend fun updateCategory(category: Category)
    suspend fun deleteCategory(category: Category)
    suspend fun addCategoryToNote(noteId: Long, categoryId: Long)
    suspend fun removeCategoryFromNote(noteId: Long, categoryId: Long)
}
