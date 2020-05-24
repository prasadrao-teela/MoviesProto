package com.goscale.assignment.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Prasad Rao on 25-05-2020 01:02
 **/
abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    open lateinit var listItem: View
    open lateinit var poster: ImageView
    open lateinit var title: TextView
    open lateinit var bookmarkToggle: ImageView
}