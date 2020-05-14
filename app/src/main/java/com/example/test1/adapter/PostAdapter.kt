package com.example.test1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test1.R
import com.example.test1.adapter.PostAdapter.PostViewHolder
import com.example.test1.databinding.PostListItemBinding
import com.example.test1.model.Data
import java.util.*

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {
    private var post: ArrayList<Data>? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PostViewHolder {
        val postListItemBinding: PostListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context),
                R.layout.post_list_item, viewGroup, false)
        return PostViewHolder(postListItemBinding)
    }

    override fun onBindViewHolder(postViewHolder: PostViewHolder, i: Int) {
        val currentStudent = post!![i]
        postViewHolder.postListItemBinding.post = currentStudent
    }

    override fun getItemCount(): Int {
        return if (post != null) {
            post!!.size
        } else {
            0
        }
    }

    fun setPostList(post: ArrayList<Data>?) {
        this.post = post
        notifyDataSetChanged()
    }

    inner class PostViewHolder(val postListItemBinding: PostListItemBinding) : ViewHolder(postListItemBinding.root)
}