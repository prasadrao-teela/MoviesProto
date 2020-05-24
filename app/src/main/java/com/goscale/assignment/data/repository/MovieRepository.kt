package com.goscale.assignment.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.goscale.assignment.data.database.dao.MovieDao
import com.goscale.assignment.data.network.Result
import com.goscale.assignment.data.network.datasource.MovieDataRemoteSource
import com.goscale.assignment.extension.convertToDatabaseEntity
import com.goscale.assignment.extension.convertToMovieModel
import com.goscale.assignment.extension.updateMovieEntity
import com.goscale.assignment.model.Movie
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Prasad Rao on 23-05-2020 22:28
 **/
class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieRemoteSource: MovieDataRemoteSource
) {

    fun findMovies(movieName: String): LiveData<Result<List<Movie>>> = resultLiveData(
        databaseQuery = {
            Transformations.map(movieDao.findMoviesWithName("%$movieName%")) { movies ->
                movies.map { movieEntity -> movieEntity.convertToMovieModel() }
            }
        },
        networkCall = {
            movieRemoteSource.findAllMovies(movieName)
        },
        saveCallResult = { searchResponse ->
            searchResponse.results?.map { movieResponse ->
                movieResponse.convertToDatabaseEntity()
            }?.let { movies ->
                movieDao.insertAll(movies)
            }
        })

    fun getMovieDetails(movieName: String): LiveData<Result<Movie?>> = resultLiveData(
        databaseQuery = {
            Transformations.map(movieDao.findMovie(movieName)) { movieEntity ->
                Timber.d("[MovieRepository] ========= $movieEntity ==============")
                movieEntity?.convertToMovieModel()
            }
        },
        networkCall = {
            movieRemoteSource.getMovieDetails(movieName)
        },
        saveCallResult = { movieDetailsResponse ->
            movieDao.getMovieWithName(movieName)?.let { movieEntity ->
                Timber.d("[MovieRepository#saveCallResult] ========= $movieEntity ==============")
                movieDao.updateMovie(movieDetailsResponse.updateMovieEntity(movieEntity))
            } ?: movieDao.updateMovie(movieDetailsResponse.convertToDatabaseEntity())
        })


    private var backgroundJob: CompletableJob? = null

    fun updateBookmarkStatus(movieName: String, bookmarkStatus: Boolean) {
        backgroundJob = Job()
        backgroundJob?.let { job ->
            CoroutineScope(Dispatchers.IO + job).launch {
                movieDao.updateBookmarkStatus(movieName, bookmarkStatus)
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