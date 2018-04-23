package com.menjoo.moviesandroid.util.extensions

import android.view.View

fun Boolean.asVisibility(): Int {
    return if (this) View.VISIBLE else View.GONE
}
