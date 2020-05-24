package com.goscale.assignment.model

/**
 * Created by Prasad Rao on 24-05-2020 19:13
 **/
data class Show(
    var title: String,
    var year: String,
    var imdbId: String,
    var poster: String,
    var releasedOn: String? = null,
    var genre: String? = null,
    var director: String? = null,
    var writer: String? = null,
    var actors: String? = null,
    var plot: String? = null,
    var awards: String? = null,
    var rating: Float = 0.0f,
    var isBookmarked: Boolean = false
)