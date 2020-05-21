package com.example.test1.constant

import com.example.test1.constant.Constants.Companion.LIST_KEY
import com.example.test1.model.notify.Hit
import java.util.*
import kotlin.collections.ArrayList

object Util {
    @JvmStatic
    fun convertData(data : List<Hit>): MutableList<Hit> {
        val result: MutableList<Hit> = ArrayList()
        data.apply {
            forEach { hit ->
                    hit.source?.run {
                        fi101.first().let { item ->
                            if (LIST_KEY.containsKey(iv104))
                               title = item.iv102 + " " + LIST_KEY[iv104 + ""]
                        }
                    }
                result.add(hit)
            }
        }
        return result
    }
}
