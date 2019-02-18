package com.tcd.androidnotepad

import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.tcd.androidnotepad.fileManagement.Note

class NoteListFragmentAdapter(private val items: List<Note>) : RecyclerView.Adapter<NoteListFragmentViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NoteListFragmentViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: NoteListFragmentViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @NonNull
    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}