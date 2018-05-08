package com.menjoo.moviesandroid

import com.menjoo.moviesandroid.injection.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class MoviesApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

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
