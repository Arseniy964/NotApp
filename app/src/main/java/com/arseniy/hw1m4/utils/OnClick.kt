package com.arseniy.hw1m4.utils

import com.arseniy.hw1m4.data.model.NoteModel

interface OnClickItem {
    fun onLongClick (noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}