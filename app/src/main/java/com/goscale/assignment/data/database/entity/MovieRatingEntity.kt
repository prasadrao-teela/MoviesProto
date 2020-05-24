package com.goscale.assignment.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Prasad Rao on 22-05-2020 21:33
 **/
@Entity(tableName = "movie_ratings")
data class MovieRatingEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "movie_id")
    val movieId: Long,

    @ColumnInfo(name = "rating_source")
    val ratingSource: String? = null,

    @ColumnInfo(name = "rating_value")
    val rating: String? = null
)