package com.devnweyi.osdatasetup.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devnweyi.osdatasetup.room.dao.CategoryDao
import com.devnweyi.osdatasetup.room.entity.CategoryEntity

@Database(entities = [CategoryEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "roomdb")
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }
}