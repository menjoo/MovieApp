package com.menjoo.moviesandroid.cinema

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.menjoo.moviesandroid.data.model.Movie

class CinemaViewModel : ViewModel(), CinemaContract.View {

    lateinit var presenter: CinemaContract.Presenter

    val observableMovies: LiveData<List<Movie>> = MutableLiveData()
    private val movies: ArrayList<Movie> = ArrayList()
    var loading: LiveData<Boolean> = MutableLiveData()
    var error: LiveData<Boolean> = MutableLiveData()

    init {
        presenter.attach(this)
    }

    override fun addMoviesToList(moviesToShow: List<Movie>) {
        movies.addAll(moviesToShow)
        (observableMovies as MutableLiveData).value = movies
    }

    override fun showLoading() {
        (loading as MutableLiveData).value = true
    }

    override fun hideLoading() {
        (loading as MutableLiveData).value = false
    }

    override fun showError() {
        (error as MutableLiveData).value = true
    }

    override fun hideError() {
        (error as MutableLiveData).value = false
    }

    override fun onCleared() {
        presenter.detach()
    }
}
