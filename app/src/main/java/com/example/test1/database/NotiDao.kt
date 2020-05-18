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
    fun getAllNoti(): LiveData<List<Hit.HitDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNoti(hitDB: Hit.HitDB)


}