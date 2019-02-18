package com.tcd.androidnotepad

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tcd.androidnotepad.fileManagement.NoteManager
import kotlinx.android.synthetic.main.fragment_edit_note.*

class EditNoteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val noteId = activity?.intent?.getStringExtra(NoteActivity.EXTRA_NOTE_ID)
        val noteTitle = editNoteTitleEditText.text.toString()
        val noteBody = editNoteBodyEditText.text.toString()

        val noteManager = NoteManager(activity)
        noteManager.save(noteId, noteTitle, noteBody)
    }

}
