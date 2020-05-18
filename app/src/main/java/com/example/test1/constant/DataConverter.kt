package com.example.test1.constant

import androidx.room.TypeConverter
import com.example.test1.model.Source
import com.google.gson.Gson


class DataConverter {

    @TypeConverter
    fun sourceToString(source: Source): String = Gson().toJson(source)

    @TypeConverter
    fun stringToSource(string: String): Source = Gson().fromJson(string, Source::class.java)

    @TypeConverter
    fun sortToString(sort: List<Double>): String? {
        return Gson().toJson(sort)
    }

    @TypeConverter
    fun stringToSort(value: String): List<Double>? {

        val objects = Gson().fromJson(value, Array<Double>::class.java) as Array<Double>
        val list = objects.toList()
        return list
    }

}