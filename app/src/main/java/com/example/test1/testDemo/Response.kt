package com.example.test1.testDemo

import com.google.gson.annotations.SerializedName

data class Response(
        @SerializedName("hits") val news: List<Data>
)

data class Data(
        val title: String,
        @SerializedName("iv103") val image: String
)