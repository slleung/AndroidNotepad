package com.tcd.androidnotepad.fileManagement

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "APP_DATABASE"
    }

    abstract fun noteDao(): NoteDao
}
