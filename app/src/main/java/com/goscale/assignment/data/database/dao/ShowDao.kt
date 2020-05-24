package com.goscale.assignment.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.goscale.assignment.data.database.entity.ShowEntity

/**
 * Created by Prasad Rao on 24-05-2020 19:35
 **/
@Dao
interface ShowDao {

    @Query("SELECT * FROM show WHERE title LIKE :title")
    fun findAllShows(title: String): LiveData<List<ShowEntity>>

    @Query("SELECT * FROM show WHERE title LIKE :title AND type LIKE :type")
    fun findAllShows(title: String, type: String): LiveData<List<ShowEntity>>

    @Query("SELECT * FROM show WHERE bookmarked LIKE :bookmarked")
    fun findAllBookmarkedShows(bookmarked: Boolean): LiveData<List<ShowEntity>>

    @Query("SELECT * FROM show WHERE title LIKE :title AND type LIKE :type LIMIT 1")
    fun findOneShowWithLiveData(title: String, type: String): LiveData<ShowEntity?>

    @Query("SELECT * FROM show WHERE title LIKE :title AND type LIKE :type LIMIT 1")
    fun findOneShow(title: String, type: String): ShowEntity?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateShow(show: ShowEntity)

    @Query("UPDATE show SET bookmarked = :bookmarkStatus WHERE title = :title AND type LIKE :type")
    suspend fun updateBookmarkStatus(title: String, type: String, bookmarkStatus: Boolean)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(shows: List<ShowEntity>)
}