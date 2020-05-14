package com.example.test1.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Shards {
    @SerializedName("total")
    @Expose
    var total: Int? = null

    @SerializedName("successful")
    @Expose
    var successful: Int? = null

    @SerializedName("skipped")
    @Expose
    var skipped: Int? = null

    @SerializedName("failed")
    @Expose
    var failed: Int? = null

}