package com.example.test1.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class Data {
    var name: String? = null
    var img: String? = null
    var noti: String? = null


    companion object {
        @JvmStatic
        @BindingAdapter("avatar")
        fun loadImage(imageView: ImageView, imageURL: String?) {
            Glide.with(imageView.context)
                    .setDefaultRequestOptions(RequestOptions()
                            .circleCrop())
                    .load(imageURL)
                    .into(imageView)
        }
    }
    override fun toString(): String {
        return "Data(name=$name, img=$img,noti=$noti)"
    }
}