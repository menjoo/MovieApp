package com.menjoo.moviesandroid.cinema

import android.graphics.drawable.BitmapDrawable
import android.support.v7.graphics.Palette
import android.view.View
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.main.cinema_item_row.view.*
import java.lang.Exception

class PerformMovieCardColoringOnSuccess(private val view: View) : Callback {

    override fun onSuccess() {
        val bitmap = (view.image.drawable as BitmapDrawable).bitmap
        Palette.from(bitmap)
                .generate({ palette ->
                    val textSwatch = palette.mutedSwatch
                    if (textSwatch != null) {
                        view.container?.setBackgroundColor(textSwatch.rgb)
                        view.title?.setTextColor(textSwatch.titleTextColor)
                        view.overview?.setTextColor(textSwatch.bodyTextColor)
                        view.moreInfoButton?.setTextColor(textSwatch.titleTextColor)
                    }
                })
    }

    override fun onError(ex: Exception) {
        // If no image was loaded, use default colors
    }
}
