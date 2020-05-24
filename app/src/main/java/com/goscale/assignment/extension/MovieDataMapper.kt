package com.goscale.assignment.extension

import com.goscale.assignment.data.database.entity.MovieEntity
import com.goscale.assignment.data.network.dto.MovieResponse
import com.goscale.assignment.model.Movie

/**
 * Created by Prasad Rao on 24-05-2020 01:16
 **/
fun MovieResponse.convertToDatabaseEntity(): MovieEntity {
    return MovieEntity(
        actors = this.actors,
        awards = this.awards,
        boxOffice = this.boxOffice,
        country = this.country,
        dvd = this.dvd,
        director = this.director,
        genre = this.genre,
        imdbId = this.imdbId,
        imdbRating = this.imdbRating,
        imdbVotes = this.imdbVotes,
        language = this.language,
        metaScore = this.metaScore,
        plot = this.plot,
        poster = this.poster,
        production = this.production,
        rated = this.rated,
        releasedOn = this.released,
        response = this.response,
        runtime = this.runtime,
        title = this.title,
        website = this.website,
        writer = this.writer,
        yearOfRelease = this.yearOfRelease
    )
}

fun MovieResponse.updateMovieEntity(movie: MovieEntity): MovieEntity {
    return movie.apply {
        actors = this@updateMovieEntity.actors
        awards = this@updateMovieEntity.awards
        boxOffice = this@updateMovieEntity.boxOffice
        country = this@updateMovieEntity.country
        dvd = this@updateMovieEntity.dvd
        director = this@updateMovieEntity.director
        genre = this@updateMovieEntity.genre
        imdbId = this@updateMovieEntity.imdbId
        imdbRating = this@updateMovieEntity.imdbRating
        imdbVotes = this@updateMovieEntity.imdbVotes
        language = this@updateMovieEntity.language
        metaScore = this@updateMovieEntity.metaScore
        plot = this@updateMovieEntity.plot
        poster = this@updateMovieEntity.poster
        production = this@updateMovieEntity.production
        rated = this@updateMovieEntity.rated
        releasedOn = this@updateMovieEntity.released
        response = this@updateMovieEntity.response
        runtime = this@updateMovieEntity.runtime
        title = this@updateMovieEntity.title
        website = this@updateMovieEntity.website
        writer = this@updateMovieEntity.writer
        yearOfRelease = this@updateMovieEntity.yearOfRelease
    }
}

fun MovieEntity.convertToMovieModel(): Movie {
    return Movie(
        title = this.title,
        year = this.yearOfRelease!!,
        imdbId = this.imdbId!!,
        poster = this.poster!!,
        releasedOn = this.releasedOn,
        genre = this.genre,
        director = this.director,
        writer = this.writer,
        actors = this.actors,
        plot = this.plot,
        awards = this.awards,
        rating = this.imdbRating?.toFloatOrNull()?.div(2.0f) ?: 0.0f,
        isBookmarked = bookmarked
    )
}