package com.example.test1.model.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.test1.model.notify.Hit

class NotifyDataSourceFactory : DataSource.Factory<String,Hit?>() {
    var sourceLiveData = MutableLiveData<NotifyDataSource>()
    override fun create(): DataSource<String, Hit?> {
        val source = NotifyDataSource()
        sourceLiveData.postValue(source)
        return source
    }
}