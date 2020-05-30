package com.example.test1.services

import android.view.View
import com.example.test1.adapter.NotiAdapter
import com.example.test1.constant.NetworkState
import com.example.test1.model.Hit

interface onclickCallBack {
    fun onClick(view:View,pos:Int)
    fun isChecked(hit: Hit?)
    fun unChecked(hit: Hit?,pos: Int)
}