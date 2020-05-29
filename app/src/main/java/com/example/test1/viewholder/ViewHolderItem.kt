package com.example.test1.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_list_item.view.*

class ViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imgAvt = itemView.img
    var tvTitle = itemView.tv_title
}