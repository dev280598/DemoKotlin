package com.example.test1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.adapter.NotiAdapter
import com.example.test1.constant.NetworkState
import com.example.test1.databinding.ActivityMainBinding
import com.example.test1.model.Hit
import com.example.test1.services.onclickCallBack
import com.example.test1.viewholder.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(),
        onclickCallBack {
    private var mainViewModel: MainViewModel? = null
    private var notiAdapter: NotiAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerView = activityMainBinding.viewPost
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        notiAdapter = NotiAdapter(this)
        allPost
        recyclerView.adapter = notiAdapter
    }
    fun observeData(){
        mainViewModel?.getArticleLiveData()?.observeForever {
            notiAdapter?.submitList(it)
        }
        mainViewModel?.getNetWorkState()?.networkState?.observeForever {
            notiAdapter?.setNetworkState(it)
        }
    }
    private val allPost: Unit
         get() {
            mainViewModel?.allPost?.observe(this, Observer<List<Hit?>?> {
                    observeData()
            })
        }
    override fun onClick(view: View, pos: Int) {
        when (view.id) {
            R.id.retry_button -> {
                mainViewModel?.getNetWorkState()?.retry?.invoke()
            }
            R.id.bt_accept -> {
                notiAdapter?.currentList?.get(pos)?.source?.checkAccept = true
                notiAdapter?.notifyItemChanged(pos)
            }
            R.id.bt_cancel -> {
                notiAdapter?.currentList?.get(pos)?.source?.checkAccept = true
                notiAdapter?.notifyItemChanged(pos)
            }

        }
    }
}



