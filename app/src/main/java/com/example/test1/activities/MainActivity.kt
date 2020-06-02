package com.example.test1.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.R
import com.example.test1.adapter.NotiAdapter
import com.example.test1.constant.NetworkState
import com.example.test1.databinding.ActivityMainBinding

import com.example.test1.model.Hit
import com.example.test1.services.onclickCallBack
import com.example.test1.viewholder.MainCallback
import com.example.test1.viewholder.MainViewModel
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import okhttp3.internal.concurrent.Task
import java.io.Serializable


class MainActivity : AppCompatActivity(),
        onclickCallBack {
    private var mainViewModel: MainViewModel? = null
    private var notiAdapter: NotiAdapter? = null
    var list: ArrayList<Hit> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val recyclerView = activityMainBinding.viewPost
        val checkBox = findViewById(R.id.checkbox_id) as CheckBox?

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = SlideInLeftAnimator()
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        notiAdapter = NotiAdapter(this)
        allPost
        recyclerView.adapter = notiAdapter

    }

    fun observeData() {
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
            R.id.checkbox_id -> {
                notiAdapter?.currentList?.get(pos)?.source?.checkAccept = true
            }
            R.id.btn_delete -> {
                notiAdapter?.currentList?.get(pos)?.checked = true
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Androidly Alert")
                builder.setMessage("You can delete this noti")
                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    notiAdapter?.notifyItemChanged(pos)
                    notiAdapter?.notifyDataSetChanged()
                    Log.d("AAAA","size: ${notiAdapter?.currentList?.size}")
                }
                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(applicationContext,
                            android.R.string.no, Toast.LENGTH_SHORT).show()
                }
                builder.show()

            }
        }
    }

    override fun isChecked(hit: Hit?) {
        list.add(hit!!)
//            Log.d("AAAA","Hit list:" +list)
    }

    override fun unChecked(hit: Hit?, pos: Int) {
        list.remove(hit!!)
//            Log.d("AAAA","Hit list:" +list)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_one) {
            Toast.makeText(this, "Item One Clicked", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ResultActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("Hit", list as Serializable)
            intent.putExtra("BUNDLE", bundle)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}




