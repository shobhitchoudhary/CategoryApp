package com.app.categories.common

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView?.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    this?.let { imageView -> Glide.with(imageView).load(url).into(imageView) }
}

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.gone() {
    this?.visibility = View.GONE
}