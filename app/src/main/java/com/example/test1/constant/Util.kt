package com.example.test1.constant

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
                    if (hit.source?.iv104.equals(Constants.LIKE_POST)){
                        user.name = item.iv102 +  "đã bày tỏ cảm xúc về bài viết của bạn "
                        user.type = Constants.LIKE_POST
                    }
                    if (hit.source?.iv104.equals(Constants.COMMENT_POST)){
                        user.name = item.iv102 + "đã bày tỏ cảm xúc về bình luận của bạn"
                        user.type = Constants.LIKE_POST
                    }
                    if (hit.source?.iv104.equals(Constants.COMMENT_COMMENT)){
                        user.name = item.iv102 +  "đã đề cập bạn trong một bình luận"
                        user.type = Constants.LIKE_POST
                    }
                    if (hit.source?.iv104.equals(Constants.SHARE_POST)){
                        user.name = item.iv102 +  " đã phản hồi trong bình luận của bạn"
                        user.type = Constants.LIKE_POST
                    }
                }
                result.add(user)
            }
        }
        return result
    }
}
