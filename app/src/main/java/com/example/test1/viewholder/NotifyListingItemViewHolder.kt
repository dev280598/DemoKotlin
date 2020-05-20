package com.example.test1.viewholder

import androidx.databinding.ViewDataBinding
import com.example.test1.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.model.Hit



class NotifyListingItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {



    fun bindTo(data: Hit.HitDB?) {
        binding.setVariable(BR.post, data)
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