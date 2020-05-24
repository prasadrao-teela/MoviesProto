package com.goscale.assignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.goscale.assignment.R
import com.goscale.assignment.model.TvShow

/**
 * Created by Prasad Rao on 23-05-2020 13:29
 **/
class TvShowListAdapter(private var tvShows: List<TvShow>) :
    RecyclerView.Adapter<TvShowListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShows[position]
        Glide.with(holder.itemView.context)
            .load(tvShow.poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.imageTvShowPoster)
        holder.labelTvShow.text = tvShow.title
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageTvShowPoster: ImageView = view.findViewById(R.id.image_movie_poster)
        val labelTvShow: TextView = view.findViewById(R.id.label_movie)
    }

    fun updateTvShowList(tvShows: List<TvShow>) {
        this.tvShows = tvShows
        notifyDataSetChanged()
    }
}