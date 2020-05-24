package com.goscale.assignment.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.goscale.assignment.data.database.dao.ShowDao
import com.goscale.assignment.data.mapper.convertToDatabaseEntity
import com.goscale.assignment.data.mapper.convertToDomainModel
import com.goscale.assignment.data.mapper.updateDatabaseEntity
import com.goscale.assignment.data.network.Result
import com.goscale.assignment.data.network.datasource.ShowsDataRemoteSource
import com.goscale.assignment.model.Show
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * Created by Prasad Rao on 24-05-2020 21:38
 **/
class ShowsDataRepository @Inject constructor(
    private val localDataSource: ShowDao,
    private val remoteDataSource: ShowsDataRemoteSource
) {
    fun fetchAllShows(title: String): LiveData<Result<List<Show>>> =
        resultLiveData(
            databaseQuery = {
                Transformations.map(
                    localDataSource.findAllShows("%$title%")
                ) { shows ->
                    shows.map { showEntity -> showEntity.convertToDomainModel() }
                }
            },
            networkCall = {
                remoteDataSource.fetchAllShows(title, "")
            },
            saveCallResult = { networkResponse ->
                networkResponse.results?.map { networkShow ->
                    networkShow.convertToDatabaseEntity()
                }?.let { shows ->
                    localDataSource.insertAll(shows)
                }
            })

    fun fetchAllShows(title: String, type: String): LiveData<Result<List<Show>>> =
        resultLiveData(
            databaseQuery = {
                Transformations.map(
                    localDataSource.findAllShows(
                        "%$title%",
                        type
                    )
                ) { shows ->
                    shows.map { showEntity -> showEntity.convertToDomainModel() }
                }
            },
            networkCall = {
                remoteDataSource.fetchAllShows(title, type)
            },
            saveCallResult = { networkResponse ->
                networkResponse.results?.map { networkShow ->
                    networkShow.convertToDatabaseEntity()
                }?.let { shows ->
                    localDataSource.insertAll(shows)
                }
            })

    fun fetchShowDetails(title: String, type: String): LiveData<Result<Show?>> =
        resultLiveData(
            databaseQuery = {
                Transformations.map(
                    localDataSource.findOneShowWithLiveData(title, type)
                ) { showEntity ->
                    showEntity?.convertToDomainModel()
                }
            },
            networkCall = {
                remoteDataSource.fetchShowDetails(title, type)
            },
            saveCallResult = { networkResponse ->
                localDataSource.findOneShow(title, type)?.let { showEntity ->
                    localDataSource.updateShow(
                        networkResponse.updateDatabaseEntity(showEntity)
                    )
                } ?: localDataSource.updateShow(networkResponse.convertToDatabaseEntity())
            })


    private var backgroundJob: CompletableJob? = null

    fun fetchAllBookmarkedShows(): LiveData<List<Show>> {
        return liveData(Dispatchers.IO) {
            val source = localDataSource.findAllBookmarkedShows(true).map { shows ->
                shows.map { showEntity -> showEntity.convertToDomainModel() }
            }
            emitSource(source)
        }
    }

    fun updateBookmarkStatus(title: String, type: String, bookmarkStatus: Boolean) {
        backgroundJob = Job()
        backgroundJob?.let { job ->
            CoroutineScope(Dispatchers.IO + job).launch {
                localDataSource.updateBookmarkStatus(title, type, bookmarkStatus)
                withContext(Dispatchers.Main) {
                    job.cancel()
                }
            }
        }
    }

    fun cancelJobs() {
        backgroundJob?.cancel()
    }

}