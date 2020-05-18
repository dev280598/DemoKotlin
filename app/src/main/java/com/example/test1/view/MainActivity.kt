package com.example.test1.view

import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.R
import com.example.test1.adapter.NotiAdapter
import com.example.test1.database.DB
import com.example.test1.databinding.ActivityMainBinding
import com.example.test1.model.Data
import com.example.test1.model.Hit
import com.example.test1.viewholder.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private var mainViewModel: MainViewModel? = null
    private var notiAdapter: NotiAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerView = activityMainBinding.viewPost
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        var notiDao = DB.getDatabase(application).NotiDao()

        val item = Hit()
        item.index = "aaa"



        //notiDao.insert()
        notiDao.getAllDB().observeForever {
            Log.d("AAA","testSize ${it.size}")
        }





        notiAdapter = NotiAdapter()
        recyclerView.adapter = notiAdapter
        allPost

    }
    private val allPost: Unit
        private get() {
            mainViewModel!!.allPost.observe(this, Observer<List<Data?>?> {
                if(it?.size==null){
                    btn_retry.visibility=View.VISIBLE
                }else{
                    notiAdapter!!.setPostList(it as ArrayList<Data>?)

                }

            })
        }
    }


