package com.example.test1.constant


class NetworkState(val status: Status,val msg: String?) {
    enum class Status {
        RUNNING, SUCCESS, FAILED
    }
    companion object {
        var LOADED: NetworkState? = null
        var LOADING: NetworkState? = null
        var FAILED : NetworkState? =null
        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")
            LOADING = NetworkState(Status.RUNNING, "Running")
            FAILED =  NetworkState(Status.FAILED, "Failed")
        }
    }
}