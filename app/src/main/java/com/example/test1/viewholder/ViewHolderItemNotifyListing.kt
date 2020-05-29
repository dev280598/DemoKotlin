package com.example.test1.viewholder

import android.widget.CheckBox
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.BR
import com.example.test1.R
import com.example.test1.model.Hit
import com.example.test1.services.onclickCallBack

class NotifyListingItemViewHolder(val binding: ViewDataBinding, private val adapterOnclick: onclickCallBack) : RecyclerView.ViewHolder(binding.root) {
    fun bindTo(data: Hit,position: Int) {
        binding.setVariable(BR.post, data)
        binding.executePendingBindings()
        data.source.checkAccept.let {
            if (it) {
                adapterOnclick.evTest(data)
            } else {
                adapterOnclick.unchecked(data)
            }
        }
        binding.root.findViewById<CheckBox>(R.id.checkbox_id).setOnClickListener() { view->
            adapterOnclick.onClick(view,position)
        }
    }
companion object {
    fun create(binding: ViewDataBinding, adapterOnclick: onclickCallBack): NotifyListingItemViewHolder {
        return NotifyListingItemViewHolder(binding, adapterOnclick)
    }
}
}