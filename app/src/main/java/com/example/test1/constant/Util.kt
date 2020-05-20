package com.example.test1.constant

import com.example.test1.constant.Constants.Companion.LIST_KEY
import com.example.test1.model.Data
import com.example.test1.model.notify.Hit
import java.util.*

object Util {

    @JvmStatic
    fun convertData(data: List<Hit>?): List<Data> {

        val result: MutableList<Data> = ArrayList()

        data?.apply {
            forEach { hit ->
                val user = Data()
                hit.run {
                    source?.run {
                        user.noti = iv104
                        fi101?.first()?.let { item ->
                            user.img = item.iv103
                            if (LIST_KEY.containsKey(hit.source?.iv104))
                                user.name = item.iv102 + " " + LIST_KEY[user.noti + ""]

                        }
                    }
                }
                result.add(user)
            }
        }
        return result
    }
}
