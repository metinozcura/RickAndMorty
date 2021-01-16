package com.metinozcura.rickandmorty.util

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.textview.MaterialTextView
import com.metinozcura.rickandmorty.R

fun MaterialTextView.setDrawableLeft(@ColorRes res: Int) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(
        R.drawable.ic_status_circle, 0, 0, 0
    )
    compoundDrawables[0].setTint(ContextCompat.getColor(context, res))
}
