package com.example.test1.activities

import android.os.Bundle
import android.util.Log
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


class ResultActivity : AppCompatActivity() {
    private var mainViewModel: MainViewModel? = null
    private var postAdapter: NotiCheckAdapter? = null
    var list : ArrayList<Hit> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val resultBinding :ActivityResultBinding =DataBindingUtil.setContentView(this, R.layout.activity_result)
        val recyclerViewResult =resultBinding.viewResult

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        recyclerViewResult.layoutManager = LinearLayoutManager(this)
        recyclerViewResult.setHasFixedSize(true)

        val intent = intent
        val bundle = intent.getBundleExtra("BUNDLE")
        val list = bundle?.getSerializable("Hit") as ArrayList<Hit>
        Log.d("AAAAA","List:" +list)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        postAdapter = NotiCheckAdapter(list)

        recyclerViewResult.adapter = postAdapter
        Log.d("AAAAA","Size:" + " " + postAdapter?.itemCount)
    }
}




