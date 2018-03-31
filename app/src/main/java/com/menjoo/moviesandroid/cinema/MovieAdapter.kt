package com.menjoo.moviesandroid.cinema

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.menjoo.moviesandroid.BuildConfig
import com.menjoo.moviesandroid.R
import com.menjoo.moviesandroid.data.model.Movie
import com.menjoo.moviesandroid.infrastructure.extensions.inflate
import com.menjoo.moviesandroid.infrastructure.extensions.toLocalizedString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cinema_item_row.view.*
import java.text.DateFormat


class MovieAdapter(private var movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflatedView = parent.inflate(R.layout.cinema_item_row, false)
        return MovieHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val itemMovie = movies[position]
        holder.bindMovie(itemMovie)
    }

    override fun getItemCount() = movies.size

    fun setItems(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class MovieHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var movie: Movie? = null

        init {
            v.setOnClickListener(this)
        }

        fun bindMovie(movie: Movie) {
            this.movie = movie
            Picasso.get()
                    .load(BuildConfig.IMAGES_BASE_URL + movie.posterPath)
                    .placeholder(R.drawable.placeholder)
                    .centerCrop()
                    .resize(171, 253)
                    .into(view.image, PerformMovieCardColoringOnSuccess(view))

            view.title.text = movie.title
            view.releaseDate.text = movie.releaseDate?.toLocalizedString(DateFormat.LONG)
            view.overview.text = movie.overview
            view.image.contentDescription = view.resources.getString(R.string.moviePosterDescription, movie.title)
        }

        override fun onClick(v: View) {

        }

        companion object {
            private val MOVIE_KEY = "MOVIE"
        }
    }
}
