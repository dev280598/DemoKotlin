package com.example.test1.model.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.test1.database.NotiDao
import com.example.test1.model.notify.Hit
import com.example.test1.services.NotifyService
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.Executor

class NotifyDataSourceFactory(var user:String,var scope: CoroutineScope?, val service: NotifyService?,var dao: NotiDao?,var retryExecutor: Executor?) : DataSource.Factory<String, Hit>() {
    var sourceLiveData = MutableLiveData<NotifyDataSource>()
    override fun create(): DataSource<String, Hit> {
        val source = NotifyDataSource(user, scope, service, dao, retryExecutor)
        sourceLiveData.postValue(source)
        return source
    }
}