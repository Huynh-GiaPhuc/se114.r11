package com.smartnote.app.data.repository

import com.smartnote.app.data.local.dao.CategoryDao
import com.smartnote.app.data.local.entity.NoteCategoryCrossRef
import com.smartnote.app.data.local.entity.toDomainModel
import com.smartnote.app.data.local.entity.toEntity
import com.smartnote.app.domain.model.Category
import com.smartnote.app.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun getCategoryById(id: Long): Flow<Category?> {
        return categoryDao.getCategoryById(id).map { it?.toDomainModel() }
    }

    override suspend fun insertCategory(category: Category): Long {
        return categoryDao.insertCategory(category.toEntity())
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category.toEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category.toEntity())
    }

    override suspend fun addCategoryToNote(noteId: Long, categoryId: Long) {
        categoryDao.insertNoteCategoryCrossRef(
            NoteCategoryCrossRef(noteId = noteId, categoryId = categoryId)
        )
    }

    override suspend fun removeCategoryFromNote(noteId: Long, categoryId: Long) {
        categoryDao.deleteNoteCategoryCrossRef(
            NoteCategoryCrossRef(noteId = noteId, categoryId = categoryId)
        )
    }
}
