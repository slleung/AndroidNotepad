package com.tcd.androidnotepad

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tcd.androidnotepad.fileManagement.NoteManager

class NoteListFragment : Fragment() {

    var adapter: NoteListFragmentAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nm = NoteManager(activity)
    }

}