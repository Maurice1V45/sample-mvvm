package com.mivas.samplemvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mivas.samplemvvm.database.model.Note
import com.mivas.samplemvvm.database.dao.NoteDao
import org.jetbrains.anko.doAsync

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase? {
            if (instance == null) {
                synchronized(NoteDatabase::class) {
                    instance =
                        Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                doAsync {
                    instance?.noteDao()?.run {
                        insert(Note("title 1", "description 1", 1))
                        insert(Note("title 2", "description 2", 2))
                        insert(Note("title 3", "description 3", 3))
                    }
                }
            }
        }
    }

}