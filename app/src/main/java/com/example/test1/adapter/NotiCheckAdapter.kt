package com.example.test1.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.test1.R
import com.example.test1.constant.Constants.Companion.LIST_KEY
import com.example.test1.databinding.ItemEmptyBinding
import com.example.test1.databinding.PostListItemBinding
import com.example.test1.model.Hit
import com.example.test1.constant.NetworkState
import com.example.test1.services.onclickCallBack
import com.example.test1.viewholder.*
import com.squareup.picasso.Picasso
import kotlin.coroutines.coroutineContext

class NotiCheckAdapter(val notis: ArrayList<Hit>) : RecyclerView.Adapter<ViewHolder>() {
    private val EMPTY_ITEM = 0
    private val NORMAL_ITEM = 1
    override fun getItemViewType(position: Int): Int {
        return if (LIST_KEY.containsKey(notis[position].source.iv104)
        ) {
            NORMAL_ITEM
        } else {
            EMPTY_ITEM
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        return if (viewType == NORMAL_ITEM) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_noti, parent, false)
            ViewHolderItem(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
            ViewHolderItemEmpty(view)
        }
    }
    override fun getItemCount(): Int {
        return notis.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (holder.itemViewType == NORMAL_ITEM) {
            val viewHolder = holder as ViewHolderItem
            if (LIST_KEY.containsKey(notis[position].source.iv104)) {
                viewHolder.tvTitle.text = notis[position].source.fi101[0].iv102 + " " + LIST_KEY[notis[position].source.iv104 + ""]

                Glide.with(viewHolder.itemView).setDefaultRequestOptions(RequestOptions().circleCrop())
                        .load(notis[position].source.fi101[0].iv103)
                        .into(viewHolder.imgAvt)
            } else {
                 holder as ViewHolderItemEmpty
            }
        }
    }
}


