package com.goscale.assignment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.goscale.assignment.data.database.dao.ShowDao
import com.goscale.assignment.data.database.entity.ShowEntity

/**
 * Created by Prasad Rao on 24-05-2020 14:43
 **/
@Database(entities = [ShowEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun showDataDao(): ShowDao
}