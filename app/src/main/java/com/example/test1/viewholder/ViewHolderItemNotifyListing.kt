package com.example.test1.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.model.notify.Hit


class NotifyListingItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bindTo(data: Hit?) {
       // binding.setVariable(BR.noti_listing, data)
        binding.executePendingBindings()
        binding.let {

        }
    }

    companion object {
        fun create(binding: ViewDataBinding): NotifyListingItemViewHolder {
            return NotifyListingItemViewHolder(binding)
        }
    }
}