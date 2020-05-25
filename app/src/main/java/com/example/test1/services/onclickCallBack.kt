package com.example.test1.services

import android.view.View
import com.example.test1.adapter.NotiAdapter
import com.example.test1.constant.NetworkState
import com.example.test1.model.Hit

interface onclickCallBack {
    fun onClick(view:View,pos:Int)
    fun evTest(view:View,hit: Hit)

}