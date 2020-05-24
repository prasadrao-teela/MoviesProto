package com.goscale.assignment.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.goscale.assignment.data.database.entity.MovieEntity

/**
 * Created by Prasad Rao on 22-05-2020 23:24
 **/
@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE title LIKE :movieName")
    fun findMoviesWithName(movieName: String): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE title LIKE :movieName LIMIT 1")
    fun findMovie(movieName: String): LiveData<MovieEntity?>

    @Query("SELECT * FROM movie WHERE title LIKE :movieName LIMIT 1")
    suspend fun getMovieWithName(movieName: String): MovieEntity?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: MovieEntity)

    @Query("UPDATE movie SET bookmarked = :bookmarkStatus WHERE title = :movieName")
    suspend fun updateBookmarkStatus(movieName: String, bookmarkStatus: Boolean)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<MovieEntity>)
}