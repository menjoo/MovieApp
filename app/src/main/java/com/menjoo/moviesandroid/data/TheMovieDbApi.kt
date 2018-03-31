package com.menjoo.moviesandroid.data

import com.menjoo.moviesandroid.data.model.SearchResult
import io.reactivex.Flowable
import retrofit2.http.GET

interface TheMovieDbApi {

    @GET("discover/movie?primary_release_date.gte=2018-03-01&primary_release_date.lte=2018-03-25")
    fun getMoviesInCinema(): Flowable<SearchResult>
}
