package com.example.test1.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.R
import com.example.test1.adapter.NotiCheckAdapter
import com.example.test1.databinding.ActivityResultBinding
import com.example.test1.model.Hit
import com.example.test1.viewholder.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_result.toolbar


class ResultActivity : AppCompatActivity() {
    private var mainViewModel: MainViewModel? = null
    private var postAdapter: NotiCheckAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val resultBinding :ActivityResultBinding =DataBindingUtil.setContentView(this, R.layout.activity_result)
        val recyclerViewResult =resultBinding.viewResult


        setSupportActionBar(toolbar)

        recyclerViewResult.layoutManager = LinearLayoutManager(this)
        recyclerViewResult.setHasFixedSize(true)

        val intent = intent
        val bundle = intent.getBundleExtra("BUNDLE")
        @Suppress("UNCHECKED_CAST") val list = bundle?.getSerializable("Hit") as ArrayList<Hit>
        Log.d("AAAAA", "List:${list.size}")

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        postAdapter = NotiCheckAdapter(list)

        recyclerViewResult.adapter = postAdapter
        Log.d("AAAAA","Size:" + " " + postAdapter?.itemCount)
    }
}




