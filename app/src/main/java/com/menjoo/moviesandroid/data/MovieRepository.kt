package com.menjoo.moviesandroid.data

import com.menjoo.moviesandroid.data.model.SearchResult
import io.reactivex.Flowable

class MovieRepository(private val movieDbApi: TheMovieDbApi) {

    fun getMoviesNowInCinema(): Flowable<SearchResult> {
        return movieDbApi.getMoviesInCinema()
    }
}
