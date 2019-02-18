package com.tcd.androidnotepad.fileManagement

import android.arch.persistence.room.Room
import android.content.Context
import android.os.*
import android.support.annotation.WorkerThread
import java.io.*
import java.util.*


/**
 * This class is the interface between all file operations.
 * It should also manage and update the database accordingly.
 */
class NoteManager(context: Context?) {

    companion object {
        const val MSG_SAVE_NOTE = 1
        const val MSG_DELETE_NOTE = 2   //TODO: Implement delete note feature

        const val KEY_NOTE_ID = "NoteManager::KEY_NOTE_ID"
        const val KEY_NOTE_TITLE = "NoteManager::KEY_NOTE_TITLE"
        const val KEY_NOTE_BODY = "NoteManager::KEY_NOTE_BODY"
    }

    private var workHandler: WorkHandler? = null

    init {
        val workHandlerThread = HandlerThread("FileManagerWorkHandlerThread")
        workHandlerThread.start()
        workHandler = WorkHandler(context, workHandlerThread.looper)
    }

    fun save(id: String?, title: String, body: String) {
        val bundle = Bundle()
        bundle.putString(KEY_NOTE_ID, id)
        bundle.putString(KEY_NOTE_TITLE, title)
        bundle.putString(KEY_NOTE_BODY, body)

        val msg = workHandler?.obtainMessage()
        msg?.let {
            msg.what = MSG_SAVE_NOTE
            msg.data = bundle
            msg.sendToTarget()
        }
    }

    private class WorkHandler(private val context: Context?, looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                MSG_SAVE_NOTE -> {
                    val data = msg.data
                    val id = data.getString(KEY_NOTE_ID)
                    val title = data.getString(KEY_NOTE_TITLE)
                    val body = data.getString(KEY_NOTE_BODY)

                    handleSaveNote(id, title!!, body!!)
                }

                MSG_DELETE_NOTE -> {
                    // To be implemented
                }

                else -> {
                    super.handleMessage(msg)
                }
            }
        }

        /**
         * This method will save the title into the database and create
         * a unique file name for the file. The body will be stored as a plain
         * text file in the internal directory. The last modified date will
         * also be stored in the database.
         *
         * If the title is empty, we will by default set the title to be "Untitled".
         *
         * @param id The id of the note. If the id is null, create a new note.
         *              Otherwise update the note with the id.
         * @param title The title of the note.
         * @param body The body of the note.
         */
        @WorkerThread
        private fun handleSaveNote(id: String?, title: String, body: String) {
            if (id == null) {
                // Create a new note
                val noteId = UUID.randomUUID().toString()
                val noteTitle = if (title.isEmpty()) "Untitled" else title
                val lastModifiedDate = System.currentTimeMillis()

                writeToInternalDir(noteId, body)

                context?.let {
                    val db = Room.databaseBuilder(
                        it,
                        AppDatabase::class.java,
                        AppDatabase.DATABASE_NAME
                    ).build()

                    db.noteDao().insertAll(Note(noteId, noteTitle, lastModifiedDate))
                }

            } else {
                // Update a note
                val noteTitle = if (title.isEmpty()) "Untitled" else title
                val lastModifiedDate = System.currentTimeMillis()

                writeToInternalDir(id, body)

                context?.let {
                    val db = Room.databaseBuilder(
                        it,
                        AppDatabase::class.java,
                        AppDatabase.DATABASE_NAME
                    ).build()

                    db.noteDao().update(Note(id, noteTitle, lastModifiedDate))
                }
            }
        }

        @WorkerThread
        private fun writeToInternalDir(fileName: String, data: String) {
            if (fileName.isBlank()) {
                throw IOException("File name is blank.")
            }

            context?.let {
                val internalDir = context.filesDir
                val file = File(internalDir, fileName)

                // For now we always rewrite the file
                if (file.exists()) {
                    file.delete()
                }

                val fileOutputStream = FileOutputStream(file)
                val outputStreamWriter = OutputStreamWriter(fileOutputStream)
                val bufferedWriter = BufferedWriter(outputStreamWriter)

                bufferedWriter.write(data)
                bufferedWriter.close()
            }
        }
    }

}
