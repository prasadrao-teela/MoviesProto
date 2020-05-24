package com.goscale.assignment.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Prasad Rao on 22-05-2020 23:26
 **/
@Entity(tableName = "tv_show")
data class TvShowEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tv_show_id")
    val tvShowId: Long = 0,

    @ColumnInfo(name = "actors")
    val actors: String? = null,

    @ColumnInfo(name = "awards")
    val awards: String? = null,

    @ColumnInfo(name = "country")
    val country: String? = null,

    @ColumnInfo(name = "director")
    val director: String? = null,

    @ColumnInfo(name = "genre")
    val genre: String? = null,

    @ColumnInfo(name = "imdb_id")
    val imdbId: String? = null,

    @ColumnInfo(name = "imdb_rating")
    val imdbRating: String? = null,

    @ColumnInfo(name = "imdb_votes")
    val imdbVotes: String? = null,

    @ColumnInfo(name = "language")
    val language: String? = null,

    @ColumnInfo(name = "meta_score")
    val metaScore: String? = null,

    @ColumnInfo(name = "plot")
    val plot: String? = null,

    @ColumnInfo(name = "poster")
    val poster: String? = null,

    @ColumnInfo(name = "rating")
    val rating: String? = null,

    @ColumnInfo(name = "released_on")
    val releasedOn: String? = null,

    @ColumnInfo(name = "response")
    val response: String? = null,

    @ColumnInfo(name = "run_time")
    val runtime: String? = null,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "total_seasons")
    val totalSeasons: String? = null,

    @ColumnInfo(name = "writer")
    val writerName: String? = null,

    @ColumnInfo(name = "year_of_release")
    val yearOfRelease: String? = null
)