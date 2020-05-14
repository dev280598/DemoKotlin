package com.example.test1.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Source {
    @SerializedName("fi101")
    @Expose
    var fi101: List<Fi101>? = null

    @SerializedName("mid_")
    @Expose
    var mid: String? = null

    @SerializedName("iv107")
    @Expose
    var iv107: String? = null

    @SerializedName("iv104")
    @Expose
    var iv104: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("user")
    @Expose
    var user: String? = null

    @SerializedName("when")
    @Expose
    var `when`: String? = null
//        set(when) {
//            field = `when`
//        }

    @SerializedName("fi102")
    @Expose
    var fi102: List<Fi102>? = null

    @SerializedName("seen")
    @Expose
    var seen: Int? = null

    @SerializedName("clicked")
    @Expose
    var clicked: Int? = null

    @SerializedName("iv105")
    @Expose
    var iv105: String? = null

}