package com.example.test1.constant

class Constants {

    companion object {
        const val DEFAULT_NETWORK_PAGE_SIZE = 10
        val LIST_KEY = listKey()
        private fun listKey(): MutableMap<String, String> {
            val map: MutableMap<String, String> = mutableMapOf()
            map["halo_noti_like_post"] = "đã bày tỏ cảm xúc về bài viết của bạn"
            map["halo_noti_comment_post"] = "đã bình luận trong bài viết của bạn"
            map["halo_noti_comment_comment"] = "đã bình luận trong một bình luận của bạn"
            map["halo_noti_like_comment"] = "đã bày tỏ cảm xúc về bình luận của bạn"
            map["noti_friend_mention_user_in_comment"] = "đã đề cập bạn trong một bình luận"
            map["noti_friend_invite_like"] = "đã mời bạn thích trang"
            map["noti_user_react_comment_user_tagged"] =
                "đã bày tỏ cảm xúc về bình luận mà bạn được đề cập"
            map["noti_user_react_post_user_tagged"] =
                "đã bày tỏ cảm xúc về bài viết mà bạn được đề cập"
            map["halo_noti_share_post"] = "đã chia sẻ bài viết của bạn"
            map["halo_noti_create_post"] = "đã đề cập bạn trong một bài viết"
            return map
        }
    }
}