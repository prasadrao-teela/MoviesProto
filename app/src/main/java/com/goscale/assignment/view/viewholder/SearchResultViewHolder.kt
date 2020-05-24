package com.goscale.assignment.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.goscale.assignment.R

/**
 * Created by Prasad Rao on 25-05-2020 00:59
 **/
class SearchResultViewHolder(view: View) : BaseViewHolder(view) {
    override var listItem: View = view.findViewById(R.id.search_result_list_item)
    override var poster: ImageView = view.findViewById(R.id.image_search_result_poster)
    override var title: TextView = view.findViewById(R.id.label_search_result_title)
    override var bookmarkToggle: ImageView =
        view.findViewById(R.id.image_search_result_bookmark_toggle)
}
