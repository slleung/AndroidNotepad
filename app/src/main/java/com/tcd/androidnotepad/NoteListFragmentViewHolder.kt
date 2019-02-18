package com.tcd.androidnotepad

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_note_list.view.*

class NoteListFragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title = itemView.noteListItemTitle
    val abstract = itemView.noteListItemAbstract
}
