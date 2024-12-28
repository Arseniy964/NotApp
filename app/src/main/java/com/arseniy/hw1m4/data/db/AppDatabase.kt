package com.arseniy.hw1m4.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arseniy.hw1m4.data.db.daos.NoteDao
import com.arseniy.hw1m4.data.model.NoteModel

@Database(entities = [NoteModel::class], version = 1 )
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}