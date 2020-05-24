package com.goscale.assignment.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Prasad Rao on 22-05-2020 23:26
 **/
@Entity(tableName = "tv_show_ratings")
data class TvShowRatingEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rating_id")
    val ratingId: Long = 0,

    @ColumnInfo(name = "tv_show_id")
    val movieId: Long,

    @ColumnInfo(name = "rating_source")
    val ratingSource: String? = null,

    @ColumnInfo(name = "rating_value")
    val rating: String? = null
)