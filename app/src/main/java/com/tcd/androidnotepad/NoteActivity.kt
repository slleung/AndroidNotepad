package com.tcd.androidnotepad

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : FragmentActivity() {

    companion object {
        const val EXTRA_NOTE_ID = "com.tcd.androidnotepad.NoteActivity::EXTRA_NOTE_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_24px)
        toolbar.setNavigationOnClickListener { finish() }

        val fm = supportFragmentManager
        fm.beginTransaction()
            .replace(R.id.note_fragment, EditNoteFragment())
            .commit()
    }
}
