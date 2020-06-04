package com.example.test1.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test1.model.Hit

@Dao
interface NotiDao{
    @Query("SELECT * from noti_table")
    fun getAllDB(): List<Hit>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(hits: List<Hit>)
    @Query("DELETE FROM noti_table")
     fun deleteAll()

}