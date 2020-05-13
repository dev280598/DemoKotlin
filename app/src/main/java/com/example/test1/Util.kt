package com.example.test1

import com.example.test1.model.Data
import com.example.test1.model.Hit
import java.util.*

object Util {
    @JvmStatic
    fun convertData(data: List<Hit>?): List<Data> {
        val result: MutableList<Data> = ArrayList()
        data?.apply {
            forEach { hit ->
                val user = Data()
                hit.source?.fi101?.first()?.let { item ->
                    user.img = item.iv103
                    user.name = item.iv102
                }
                result.add(user)
            }
        }
        return result
    }
}