package com.menjoo.moviesandroid.util

import com.menjoo.moviesandroid.data.model.SearchResult

class MoviePager(private val searchResult: SearchResult?) {

    fun hasNextPage(): Boolean {
        if (searchResult != null) {
            return searchResult.page < searchResult.totalPages
        }
        return false
    }

    fun nextPage(): Int {
        if (searchResult != null) {
            return searchResult.page + 1
        }
        return 1
    }
}
