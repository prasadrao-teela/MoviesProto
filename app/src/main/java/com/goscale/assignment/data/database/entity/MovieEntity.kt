package com.goscale.assignment.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.goscale.assignment.common.constant.Constant

/**
 * Created by Prasad Rao on 22-05-2020 21:21
 **/
@Entity(tableName = "movie")
data class MovieEntity(

    @ColumnInfo(name = "actors")
    var actors: String? = null,

    @ColumnInfo(name = "awards")
    var awards: String? = null,

    @ColumnInfo(name = "box_office")
    var boxOffice: String? = null,

    @ColumnInfo(name = "country")
    var country: String? = null,

    @ColumnInfo(name = "dvd")
    var dvd: String? = null,

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

    @ColumnInfo(name = "meta_score")
    var metaScore: String? = null,

    @ColumnInfo(name = "plot")
    var plot: String? = null,

    @ColumnInfo(name = "poster")
    var poster: String? = null,

    @ColumnInfo(name = "production")
    var production: String? = null,

    @ColumnInfo(name = "rating")
    var rated: String? = null,

    @ColumnInfo(name = "released_on")
    var releasedOn: String? = null,

    @ColumnInfo(name = "response")
    var response: String? = null,

    @ColumnInfo(name = "runtime")
    var runtime: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "title")
    var title: String = Constant.DEFAULT_MOVIE_NAME,

    @ColumnInfo(name = "website")
    var website: String? = null,

    @ColumnInfo(name = "writer")
    var writer: String? = null,

    @ColumnInfo(name = "year_released")
    var yearOfRelease: String? = null,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false
)