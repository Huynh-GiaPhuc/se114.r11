package com.smartnote.app.di

import android.content.Context
import androidx.room.Room
import com.smartnote.app.data.local.dao.AttachmentDao
import com.smartnote.app.data.local.dao.CategoryDao
import com.smartnote.app.data.local.dao.NoteDao
import com.smartnote.app.data.local.database.SmartNoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSmartNoteDatabase(
        @ApplicationContext context: Context
    ): SmartNoteDatabase {
        return Room.databaseBuilder(
            context,
            SmartNoteDatabase::class.java,
            SmartNoteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(database: SmartNoteDatabase): NoteDao {
        return database.noteDao
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: SmartNoteDatabase): CategoryDao {
        return database.categoryDao
    }

    @Provides
    @Singleton
    fun provideAttachmentDao(database: SmartNoteDatabase): AttachmentDao {
        return database.attachmentDao
    }
}
