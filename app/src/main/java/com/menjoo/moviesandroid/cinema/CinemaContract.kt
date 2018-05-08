package com.menjoo.moviesandroid.cinema

import com.menjoo.moviesandroid.data.model.Movie
import com.menjoo.moviesandroid.util.mvp.BasePresenter
import com.menjoo.moviesandroid.util.mvp.BaseView

interface CinemaContract {

    interface View : BaseView<Presenter> {
        fun addMoviesToList(moviesToShow: List<Movie>)
        fun showLoading()
        fun hideLoading()
        fun showError()
        fun hideError()
    }

    interface Presenter : BasePresenter<View> {
        fun onRefreshPulled()
        fun onLoadMore()
    }
}
