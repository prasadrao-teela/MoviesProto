package com.goscale.assignment.extension

import com.goscale.assignment.data.database.entity.TvShowEntity
import com.goscale.assignment.data.network.dto.TvShowResponse
import com.goscale.assignment.model.TvShow

/**
 * Created by Prasad Rao on 24-05-2020 01:16
 **/
fun TvShowResponse.convertToTvShowModel(): TvShow {
    return TvShow(
        title = this.title!!,
        year = this.yearOfRelease!!,
        imdbId = this.imdbId!!,
        poster = this.poster!!,
        releasedOn = this.released,
        genre = this.genre,
        director = this.director,
        writer = this.writerName,
        actors = this.actors,
        plot = this.plot,
        awards = this.awards,
        rating = this.imdbRating?.toFloatOrNull()?.div(2.0f) ?: 0.0f
    )
}

fun TvShowEntity.convertToTvShowModel(): TvShow {
    return TvShow(
        title = this.title!!,
        year = this.yearOfRelease!!,
        imdbId = this.imdbId!!,
        poster = this.poster!!,
        releasedOn = this.releasedOn,
        genre = this.genre,
        director = this.director,
        writer = this.writerName,
        actors = this.actors,
        plot = this.plot,
        awards = this.awards,
        rating = this.imdbRating?.toFloatOrNull()?.div(2.0f) ?: 0.0f
    )
}