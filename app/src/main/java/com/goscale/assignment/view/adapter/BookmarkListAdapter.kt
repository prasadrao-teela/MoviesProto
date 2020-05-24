package com.goscale.assignment.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.goscale.assignment.R
import com.goscale.assignment.common.constant.Constant
import com.goscale.assignment.model.Show
import com.goscale.assignment.view.ui.details.ShowDetailsActivity
import com.goscale.assignment.view.viewholder.BaseViewHolder
import com.goscale.assignment.view.viewholder.BookmarkViewHolder
import com.goscale.assignment.view.viewholder.SearchResultViewHolder

/**
 * Created by Prasad Rao on 23-05-2020 13:28
 **/
class BookmarkListAdapter(
    private var shows: List<Show>,
    private var type: Type = Type.BOOKMARKS
) : RecyclerView.Adapter<BaseViewHolder>() {

    val bookmarkUpdate: MutableLiveData<Show> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return if (Type.BOOKMARKS == type) {
            BookmarkViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_bookmarks, parent, false)
            )
        } else {
            SearchResultViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_search_results, parent, false)
            )
        }
    }

    override fun getItemCount(): Int = shows.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
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
        holder.bookmarkToggle.isActivated = show.isBookmarked
        holder.bookmarkToggle.setOnClickListener {
            show.isBookmarked = !show.isBookmarked
            holder.bookmarkToggle.isActivated = show.isBookmarked
            bookmarkUpdate.value = show
        }
    }

    fun updateShowsData(shows: List<Show>) {
        this.shows = shows
        notifyDataSetChanged()
    }

    enum class Type {
        BOOKMARKS,
        SEARCH_RESULTS
    }
}