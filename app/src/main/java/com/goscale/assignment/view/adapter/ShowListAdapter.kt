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
import com.goscale.assignment.common.constant.Constant
import com.goscale.assignment.model.Show
import com.goscale.assignment.view.ui.details.ShowDetailsActivity

/**
 * Created by Prasad Rao on 23-05-2020 13:28
 **/
class ShowListAdapter(private var shows: List<Show>) :
    RecyclerView.Adapter<ShowListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_show, parent, false)
        )
    }

    override fun getItemCount(): Int = shows.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val show = shows[position]
        holder.listItem.setOnClickListener { view ->
            view.context.let { context ->
                context.startActivity(Intent(context, ShowDetailsActivity::class.java).apply {
                    putExtra(Constant.KEY_SHOW_TITLE, show.title)
                    putExtra(Constant.KEY_SHOW_TYPE, show.type)
                })
            }
        }
        Glide.with(holder.itemView.context)
            .load(show.poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.poster)
        holder.title.text = show.title
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listItem: View = view.findViewById(R.id.show_list_item_view)
        val poster: ImageView = view.findViewById(R.id.image_show_poster)
        val title: TextView = view.findViewById(R.id.label_show)
    }

    fun updateShowsData(shows: List<Show>) {
        this.shows = shows
        notifyDataSetChanged()
    }
}