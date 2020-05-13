package com.example.test1.model

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
    var sort: List<Long>? = null

}