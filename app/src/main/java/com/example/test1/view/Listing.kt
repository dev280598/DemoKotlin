package com.example.test1.view

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.test1.constant.NetworkState

data class Listing<T> (
        val networkState: LiveData<NetworkState>,
        val refreshState: LiveData<NetworkState>,
        val refresh: () -> Unit,
        val retry: () -> Unit

)