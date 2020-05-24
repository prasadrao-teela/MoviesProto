package com.goscale.assignment.data.network.dto

import com.google.gson.annotations.SerializedName
import com.goscale.assignment.common.constant.Constant

/**
 * Created by Prasad Rao on 22-05-2020 16:29
 **/
data class MovieResponse(

    @SerializedName("Actors")
    val actors: String? = null,

    @SerializedName("Awards")
    val awards: String? = null,

    @SerializedName("BoxOffice")
    val boxOffice: String? = null,

    @SerializedName("Country")
    val country: String? = null,

    @SerializedName("DVD")
    val dvd: String? = null,

    @SerializedName("Director")
    val director: String? = null,

    @SerializedName("Genre")
    val genre: String? = null,

    @SerializedName("imdbID")
    val imdbId: String? = null,

    @SerializedName("imdbRating")
    val imdbRating: String? = null,

    @SerializedName("imdbVotes")
    val imdbVotes: String? = null,

    @SerializedName("Language")
    val language: String? = null,

    @SerializedName("Metascore")
    val metaScore: String? = null,

    @SerializedName("Plot")
    val plot: String? = null,

    @SerializedName("Poster")
    val poster: String? = null,

    @SerializedName("Production")
    val production: String? = null,

    @SerializedName("Rated")
    val rated: String? = null,

    @SerializedName("Ratings")
    val ratings: List<RatingResponse>? = null,

    @SerializedName("Released")
    val released: String? = null,

    @SerializedName("Response")
    val response: String? = null,

    @SerializedName("Runtime")
    val runtime: String? = null,

    @SerializedName("Title")
    val title: String = Constant.DEFAULT_MOVIE_NAME,

    @SerializedName("Type")
    val type: String? = null,

    @SerializedName("Website")
    val website: String? = null,

    @SerializedName("Writer")
    val writer: String? = null,

    @SerializedName("Year")
    val yearOfRelease: String? = null
)