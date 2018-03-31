package com.menjoo.moviesandroid.data

import com.menjoo.moviesandroid.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object Injection {

    val movieRepository: MovieRepository by lazy {
        MovieRepository(createMovieDbApi())
    }

    private fun createMovieDbApi(): TheMovieDbApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(TheMovieDbApi::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(AddApiKeyInterceptor())
                .addInterceptor(createLoggingInterceptor())
                .build()
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}
