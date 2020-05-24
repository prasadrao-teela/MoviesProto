package com.goscale.assignment.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.goscale.assignment.common.constant.Constant

/**
 * Created by Prasad Rao on 24-05-2020 19:32
 **/
@Entity(tableName = "show", primaryKeys = ["title", "type"])
data class ShowEntity(

    @ColumnInfo(name = "actors")
    var actors: String? = null,

    @ColumnInfo(name = "awards")
    var awards: String? = null,

    @ColumnInfo(name = "country")
    var country: String? = null,

    @ColumnInfo(name = "director")
    var director: String? = null,

    @ColumnInfo(name = "genre")
    var genre: String? = null,

    @ColumnInfo(name = "imdb_id")
    var imdbId: String? = null,

    @ColumnInfo(name = "imdb_rating")
    var imdbRating: String? = null,

    @ColumnInfo(name = "imdb_votes")
    var imdbVotes: String? = null,

    @ColumnInfo(name = "language")
    var language: String? = null,

    @ColumnInfo(name = "plot")
    var plot: String? = null,

    @ColumnInfo(name = "poster")
    var poster: String? = null,

    @ColumnInfo(name = "rating")
    var rated: String? = null,

    @ColumnInfo(name = "released_on")
    var releasedOn: String? = null,

    @ColumnInfo(name = "response")
    var response: String? = null,

    @ColumnInfo(name = "runtime")
    var runtime: String? = null,

    @ColumnInfo(name = "title")
    var title: String = Constant.DEFAULT_SHOW_TITLE,

    @ColumnInfo(name = "type")
    var type: String = Constant.SHOW_TYPE_MOVIE,

    @ColumnInfo(name = "writer")
    var writer: String? = null,

    @ColumnInfo(name = "year_released")
    var yearOfRelease: String? = null,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false
)