package com.menjoo.moviesandroid.cinema

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.menjoo.moviesandroid.R
import com.menjoo.moviesandroid.util.EndlessRecyclerViewScrollListener
import com.menjoo.moviesandroid.util.extensions.asVisibility
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.cinema_fragment.*
import javax.inject.Inject


class CinemaFragment @Inject constructor() : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: CinemaViewModelFactory

    private lateinit var viewModel: CinemaViewModel
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cinema_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CinemaViewModel::class.java)
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

        val scrollListener: EndlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.presenter.onLoadMore()
            }

        }
        recyclerView.addOnScrollListener(scrollListener)
    }

    private fun setupPullToRefresh() {
        pullToRefreshIndicator.setOnRefreshListener { viewModel.presenter.onRefreshPulled() }
    }

    private fun observeMovieList() {
        viewModel.observableMovies.observe(this, Observer { movies ->
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
