package com.example.test1.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.R

class ViewHolderItemEmpty(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup): ViewHolderItemEmpty {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
            return ViewHolderItemEmpty(view)
        }
    }


}