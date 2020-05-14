package com.example.test1.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Post {
    @SerializedName("took")
    @Expose
    var took: Int? = null

    @SerializedName("timed_out")
    @Expose
    var timedOut: Boolean? = null

    @SerializedName("_shards")
    @Expose
    var shards: Shards? = null

    @SerializedName("hits")
    @Expose
    var hits: Hits? = null

}