package com.menjoo.moviesandroid.cinema

import com.menjoo.moviesandroid.data.model.Movie
import com.menjoo.moviesandroid.infrastructure.mvp.BasePresenter
import com.menjoo.moviesandroid.infrastructure.mvp.BaseView

interface CinemaContract {

    interface View : BaseView<Presenter> {
        fun showMoviesNowInCinema(movies: List<Movie>)
        fun showLoading()
        fun hideLoading()
        fun showError()
        fun hideError()
    }

    interface Presenter : BasePresenter {

        fun onRefreshPulled()
    }
}
