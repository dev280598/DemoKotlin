package com.example.test1.datasource

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.test1.model.Hit
import com.example.test1.services.onclickCallBack

class NotifyDataSourceFactory : DataSource.Factory<String, Hit?>() {
    var sourceLiveData: MutableLiveData<NotifyDataSource> = MutableLiveData()
    override fun create(): DataSource<String, Hit?> {
        val source = NotifyDataSource()
        sourceLiveData.postValue(source)
        return source
    }
}