package com.example.test1.services

import androidx.lifecycle.LiveData
import com.example.test1.model.notify.Hit
import com.example.test1.model.notify.NotifyResponse
import com.example.test1.utils.Listing
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.Executor

interface NotificationRepositoryInterface {
   suspend fun getData(): NotifyResponse
    fun getListingNotifyOnl(usre: String)
    fun getDB(): LiveData<List<Hit>>
}