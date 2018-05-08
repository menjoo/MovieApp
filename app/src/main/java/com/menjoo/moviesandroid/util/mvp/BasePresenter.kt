package com.menjoo.moviesandroid.util.mvp

interface BasePresenter<T> {

    fun attach(view: T)

    fun detach()
}
