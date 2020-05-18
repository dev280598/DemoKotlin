package com.example.test1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.test1.constant.DataConverter
import com.example.test1.model.Hit

@Database(entities = [Hit.HitDB::class], version = 2, exportSchema = false)
@TypeConverters(DataConverter::class)

abstract class DB : RoomDatabase() {
    abstract fun NotiDao(): NotiDao

    companion object {
        @Volatile
        private var INSTANCE: DB? = null

        fun getDatabase(context: Context): DB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DB::class.java,
                        "word_database"
                ).fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
