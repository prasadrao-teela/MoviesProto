package com.goscale.assignment.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Created by Prasad Rao on 22-05-2020 23:21
 **/
data class MovieWithRatings(

    @Embedded
    val movie: MovieEntity,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "movie_id"
    )
    val movieRatings: List<MovieRatingEntity>
)