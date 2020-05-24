package com.goscale.assignment.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.goscale.assignment.data.database.entity.TvShowEntity

/**
 * Created by Prasad Rao on 22-05-2020 23:35
 **/
@Dao
interface TvShowDao {

    @Query("SELECT * FROM tv_show")
    fun getAllTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tv_show WHERE title LIKE :tvShowName")
    fun findTvShowsWithName(tvShowName: String): LiveData<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<TvShowEntity>)
}