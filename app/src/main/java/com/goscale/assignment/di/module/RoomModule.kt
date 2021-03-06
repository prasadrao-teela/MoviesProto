package com.goscale.assignment.di.module

import androidx.room.Room
import com.goscale.assignment.GoScaleApplication
import com.goscale.assignment.data.database.AppDatabase
import com.goscale.assignment.data.database.dao.ShowDao
import com.goscale.assignment.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by Prasad Rao on 24-05-2020 14:15
 **/
@Module
class RoomModule(private val context: GoScaleApplication) {

    @ApplicationScope
    @Provides
    fun provideRoomDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "go_scale_database"
        ).build()
    }

    @ApplicationScope
    @Provides
    fun provideShowDataDao(database: AppDatabase): ShowDao {
        return database.showDataDao()
    }
}