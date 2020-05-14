package com.example.test1.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hits {
    @SerializedName("total")
    @Expose
    var total: Int? = null

    @SerializedName("max_score")
    @Expose
    var maxScore: Any? = null

    @SerializedName("hits")
    @Expose
    var hits: List<Hit>? = null

}