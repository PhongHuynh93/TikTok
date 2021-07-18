package com.umbrella.tiktok.extension

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.core.content.res.use
import com.umbrella.tiktok.R

fun Context.getDimen(dimenRes: Int): Float {
    return resources.getDimension(dimenRes)
}

val Context.spacePrettySmall
    get() = getDimen(R.dimen.space_pretty_small)
val Context.spaceNormal
    get() = getDimen(R.dimen.space_normal)
val Context.spaceSmall
    get() = getDimen(R.dimen.space_small)
val Context.spaceLarge
    get() = getDimen(R.dimen.space_large)
val Context.spaceTiny
    get() = getDimen(R.dimen.space_tiny)
val Context.spaceAboveNormal
    get() = getDimen(R.dimen.space_above_normal)
val Context.spaceBetweenText
    get() = getDimen(R.dimen.space_between_text)

val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)

fun Context.getColorAttr(@AttrRes colorAttr: Int): Int {
    return obtainStyledAttributes(
        intArrayOf(colorAttr)
    ).use {
        it.getColor(0, Color.MAGENTA)
    }
}