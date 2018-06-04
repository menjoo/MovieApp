package com.menjoo.moviesandroid

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.picasso.Picasso


class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

        Picasso.setSingletonInstance(
                Picasso.Builder(this)
                        .indicatorsEnabled(BuildConfig.DEBUG)
                        .build())
    }
}
