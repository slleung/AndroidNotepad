package com.tcd.androidnotepad.fileManagement

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
data class Note (
    @PrimaryKey
    @ColumnInfo(name = "note_id")
    var noteId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "last_modified_date")
    var lastModifiedDate: Long
)
