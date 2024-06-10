package com.example.client.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [NameEntity::class],
    version = 1
)
abstract class MainDb : RoomDatabase() {
    abstract val dao: Dao

    companion object {
        fun createDataBase(context: Context): MainDb {
            return Room.databaseBuilder(
                context = context,
                MainDb::class.java,
                "client.db"
            ).build()
        }
    }
}