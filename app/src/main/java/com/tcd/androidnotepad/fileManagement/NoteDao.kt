package com.tcd.androidnotepad.fileManagement

import android.arch.persistence.room.*

@Dao
interface NoteDao {
    @Query("Select * from notes")
    fun getAll(): List<Note>

    @Insert
    fun insertAll(vararg notes: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(user: Note)
}
