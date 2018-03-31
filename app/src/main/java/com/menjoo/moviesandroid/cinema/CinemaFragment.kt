package com.menjoo.moviesandroid.cinema

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.menjoo.moviesandroid.R
import com.menjoo.moviesandroid.infrastructure.extensions.asVisibility
import kotlinx.android.synthetic.main.cinema_fragment.*

class CinemaFragment : Fragment() {

    companion object {
        private val NUMBER_OF_COLUMNS: Int = 2
    }

    private lateinit var viewModel: CinemaViewModel
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.cinema_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(CinemaViewModel::class.java)
        setupRecyclerView()
        setupPullToRefresh()
        observeMovieList()
        observeLoading()
        observeErrors()
    }

    private fun setupRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = MovieAdapter(ArrayList())
        recyclerView.adapter = adapter
    }

    private fun setupPullToRefresh() {
        pullToRefreshIndicator.setOnRefreshListener { viewModel.presenter.onRefreshPulled() }
    }

    private fun observeMovieList() {
        viewModel.moviesNowInCinema.observe(this, Observer { movies ->
            adapter.setItems(movies ?: emptyList())
        })
    }

    private fun observeLoading() {
        viewModel.loading.observe(this, Observer { isLoading ->
            pullToRefreshIndicator.isRefreshing = isLoading ?: false
        })
    }

    private fun observeErrors() {
        viewModel.error.observe(this, Observer { hasError ->
            errorMessage.visibility = hasError?.asVisibility() ?: View.GONE
        })
    }
}
