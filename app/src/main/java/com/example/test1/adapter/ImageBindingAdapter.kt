package com.example.test1.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso

object ImageBindingAdapter {
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