package com.menjoo.moviesandroid.cinema

import com.menjoo.moviesandroid.data.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CinemaPresenter(private val movieRepository: MovieRepository,
                      private val view: CinemaContract.View) : CinemaContract.Presenter {
    private val disposables = CompositeDisposable()

    init {
        view.presenter = this
    }

    override fun start() {
        loadMovies()
    }

    private fun loadMovies() {
        view.hideError()
        view.showLoading()
        disposables.add(movieRepository.getMoviesNowInCinema()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { searchResult ->
                            view.showMoviesNowInCinema(searchResult.results)
                        },
                        onError = {
                            view.hideLoading()
                            view.showError()
                        },
                        onComplete = {
                            view.hideLoading()
                        }
                )
        )
    }

    override fun onRefreshPulled() {
        loadMovies()
    }

    override fun stop() {
        disposables.dispose()
    }
}
