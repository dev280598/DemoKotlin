package com.example.test1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.test1.constant.DataConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hit {
    @SerializedName("_index")
    @Expose
    var index: String? = null

    @SerializedName("_type")
    @Expose
    var type: String? = null

    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("_score")
    @Expose
    var score: Any? = null

    @SerializedName("_source")
    @Expose
    var source: Source? = null

    @SerializedName("sort")
    @Expose
    var sort: List<Double>? = null

    @Entity(tableName = "noti_table")
    data class HitDB(

            @PrimaryKey @ColumnInfo(name = "id") val _id: String,
            @ColumnInfo(name = "index") val _index: String,
            @ColumnInfo(name = "type") val _type: String)

//            @TypeConverters(DataConverter::class)
//            @ColumnInfo(name = "sort") val sort: List<Double>?,
//
//            @TypeConverters(DataConverter::class)
//            @ColumnInfo(name = "source") val source: Source?)

}