package com.arseniy.hw1m4

import android.app.Application
import androidx.room.Room
import com.arseniy.hw1m4.data.db.AppDatabase
import com.arseniy.hw1m4.utils.PreferenceHelper

class App : Application() {
    companion object {
        var appDatabase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper(this)
        sharedPreferences.unit(this)
        getInstate()
    }

    fun getInstate(): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = applicationContext?.let { context ->
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "note.datebase"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}