package com.furtak.androidclasses.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.furtak.androidclasses.database.dao.ChildDao
import com.furtak.androidclasses.database.model.ChildEntity

@Database(
    version = 1,
    entities = [
        ChildEntity::class,
    ],
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getChildDao(): ChildDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "app_database"
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}