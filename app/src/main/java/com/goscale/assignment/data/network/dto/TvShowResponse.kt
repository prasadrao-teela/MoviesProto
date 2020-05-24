package com.goscale.assignment.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Prasad Rao on 22-05-2020 16:29
 **/
data class TvShowResponse(
    @SerializedName("Actors")
    val actors: String? = null,

    @SerializedName("Awards")
    val awards: String? = null,

    @SerializedName("Country")
    val country: String? = null,

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

    @SerializedName("Rated")
    val rating: String? = null,

    @SerializedName("Ratings")
    val ratings: List<RatingResponse>? = null,

    @SerializedName("Released")
    val released: String? = null,

    @SerializedName("Response")
    val response: String? = null,

    @SerializedName("Runtime")
    val runtime: String? = null,

    @SerializedName("Title")
    val title: String? = null,

    @SerializedName("totalSeasons")
    val totalSeasons: String? = null,

    @SerializedName("Writer")
    val writerName: String? = null,

    @SerializedName("Year")
    val yearOfRelease: String? = null
)