package com.ph.bookmarkofall.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ph.bookmarkofall.data.di.GlideApp

@BindingAdapter("imageUrl")
fun imageUrl(view : ImageView,src : String){
    if(!src.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(src)
            .into(view)
    }
}