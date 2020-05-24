package com.goscale.assignment.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.goscale.assignment.R
import com.goscale.assignment.model.Movie
import com.goscale.assignment.view.ui.details.MovieDetailsActivity

/**
 * Created by Prasad Rao on 23-05-2020 13:28
 **/
class MovieListAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>(), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.listItem.setOnClickListener { view ->
            view.context.let { context ->
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("movie_name", movie.title)
                context.startActivity(intent)
            }
        }
        Glide.with(holder.itemView.context)
            .load(movie.poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.imageMoviePoster)
        holder.labelMovie.text = movie.title
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listItem: View = view.findViewById(R.id.movie_list_item_view)
        val imageMoviePoster: ImageView = view.findViewById(R.id.image_movie_poster)
        val labelMovie: TextView = view.findViewById(R.id.label_movie)
    }

    fun updateMovieList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        v?.context?.let { context ->
            context.startActivity(Intent(context, MovieDetailsActivity::class.java))
        }
    }
}