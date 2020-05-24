package com.goscale.assignment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.goscale.assignment.data.database.dao.MovieDao
import com.goscale.assignment.data.database.dao.TvShowDao
import com.goscale.assignment.data.database.entity.MovieEntity
import com.goscale.assignment.data.database.entity.TvShowEntity

/**
 * Created by Prasad Rao on 24-05-2020 14:43
 **/
@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TvShowDao
}