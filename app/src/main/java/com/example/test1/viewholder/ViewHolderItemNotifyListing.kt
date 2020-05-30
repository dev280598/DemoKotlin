package com.example.test1.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.BR
import com.example.test1.model.Hit
import com.example.test1.services.onclickCallBack
import kotlinx.android.synthetic.main.post_list_item.view.*

class NotifyListingItemViewHolder(val binding: ViewDataBinding, private val adapterOnclick: onclickCallBack) : RecyclerView.ViewHolder(binding.root) {
    fun bindTo(data: Hit?,position: Int) {
        binding.setVariable(BR.post, data)
        binding.executePendingBindings()
//        data?.source!!.checkAccept.let {
//            if (it) {
//                adapterOnclick.isChecked(data)
//            } else {
//
//            }
//        }
//        binding.root.checkbox_id.setOnCheckedChangeListener { v, isChecked ->
//            if (isChecked)
//            {
//               adapterOnclick.isChecked(data)
//            }
//
//        }
        binding.root.checkbox_id.setOnClickListener {
            adapterOnclick.onClick(it,position)
            val isChecked: Boolean = binding.root.checkbox_id.isChecked
            data?.source!!.checkAccept.let {
                if (isChecked) {
                    adapterOnclick.isChecked(data)
                } else {
                    adapterOnclick.unChecked(data,position)

                }
            }
        }

    }
companion object {
    fun create(binding: ViewDataBinding, adapterOnclick: onclickCallBack): NotifyListingItemViewHolder {
        return NotifyListingItemViewHolder(binding, adapterOnclick)
    }
}
}