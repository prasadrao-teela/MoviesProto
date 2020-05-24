package com.goscale.assignment.data.mapper

import com.goscale.assignment.data.database.entity.ShowEntity
import com.goscale.assignment.data.network.dto.ShowResponse
import com.goscale.assignment.model.Show

/**
 * Created by Prasad Rao on 24-05-2020 01:16
 **/
fun ShowResponse.convertToDatabaseEntity(): ShowEntity {
    return ShowEntity(
        actors = this.actors,
        awards = this.awards,
        country = this.country,
        director = this.director,
        genre = this.genre,
        imdbId = this.imdbId,
        imdbRating = this.imdbRating,
        imdbVotes = this.imdbVotes,
        language = this.language,
        plot = this.plot,
        poster = this.poster,
        rated = this.rated,
        releasedOn = this.releasedOn,
        response = this.response,
        title = this.title,
        type = this.type,
        writer = this.writer,
        yearOfRelease = this.yearOfRelease
    )
}

fun ShowResponse.updateDatabaseEntity(show: ShowEntity): ShowEntity {
    return show.apply {
        actors = this@updateDatabaseEntity.actors
        awards = this@updateDatabaseEntity.awards
        country = this@updateDatabaseEntity.country
        director = this@updateDatabaseEntity.director
        genre = this@updateDatabaseEntity.genre
        imdbId = this@updateDatabaseEntity.imdbId
        imdbRating = this@updateDatabaseEntity.imdbRating
        imdbVotes = this@updateDatabaseEntity.imdbVotes
        language = this@updateDatabaseEntity.language
        plot = this@updateDatabaseEntity.plot
        poster = this@updateDatabaseEntity.poster
        rated = this@updateDatabaseEntity.rated
        response = this@updateDatabaseEntity.response
        runtime = this@updateDatabaseEntity.runtime
        title = this@updateDatabaseEntity.title
        type = this@updateDatabaseEntity.type
        writer = this@updateDatabaseEntity.writer
        yearOfRelease = this@updateDatabaseEntity.yearOfRelease
    }
}

fun ShowEntity.convertToDomainModel(): Show {
    return Show(
        actors = this.actors,
        awards = this.awards,
        director = this.director,
        genre = this.genre,
        imdbId = this.imdbId!!,
        isBookmarked = bookmarked,
        plot = this.plot,
        poster = this.poster!!,
        title = this.title,
        type = this.type,
        rating = this.imdbRating?.toFloatOrNull()?.div(2.0f) ?: 0.0f,
        releasedOn = this.releasedOn,
        writer = this.writer,
        year = this.yearOfRelease!!
    )
}