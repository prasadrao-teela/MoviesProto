package com.goscale.assignment.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.goscale.assignment.R

/**
 * Created by Prasad Rao on 25-05-2020 00:58
 **/
class BookmarkViewHolder(view: View) : BaseViewHolder(view) {
    override var listItem: View = view.findViewById(R.id.bookmark_show_list_item)
    override var poster: ImageView = view.findViewById(R.id.image_bookmarked_poster)
    override var title: TextView = view.findViewById(R.id.label_bookmarked_show)
    override var bookmarkToggle: ImageView = view.findViewById(R.id.image_bookmark_toggle)
}