package com.metinozcura.rickandmorty.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.metinozcura.rickandmorty.R
import com.metinozcura.rickandmorty.data.model.Status

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("status")
fun MaterialTextView.status(status: Status) {
    text = status.toString()
    when (status) {
        Status.ALIVE -> setDrawableTint(R.color.green_a700)
        Status.DEAD -> setDrawableTint(R.color.red_a700)
        Status.UNKNOWN -> setDrawableTint(R.color.gray_700)
    }
}