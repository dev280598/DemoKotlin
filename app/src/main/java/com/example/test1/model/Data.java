package com.example.test1.model;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.test1.BR;
import com.example.test1.R;

public class Data  {
    String name;
    String img;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;

    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    @BindingAdapter({ "avatar" })
    public static void loadImage(ImageView imageView, String imageURL) {
        Glide.with(imageView.getContext())
                .load(imageURL)
                .into(imageView);
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
