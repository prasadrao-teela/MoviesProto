package com.goscale.assignment.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Created by Prasad Rao on 22-05-2020 23:26
 **/
data class TvShowWithRatings(

    @Embedded
    val tvShow: TvShowEntity,

    @Relation(
        parentColumn = "tv_show_id",
        entityColumn = "tv_show_id"
    )
    val tvShowRatings: List<TvShowRatingEntity>
)