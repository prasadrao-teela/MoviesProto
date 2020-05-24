package com.goscale.assignment.data.repository

import com.goscale.assignment.common.constant.Constant
import com.goscale.assignment.data.database.dao.ShowDao
import com.goscale.assignment.data.network.datasource.ShowsDataRemoteSource
import javax.inject.Inject

/**
 * Created by Prasad Rao on 23-05-2020 22:28
 **/
class TvShowRepository @Inject constructor(
    localDataSource: ShowDao,
    remoteDataSource: ShowsDataRemoteSource
) : DataRepository(localDataSource, remoteDataSource) {

    override fun type(): String = Constant.SHOW_TYPE_SERIES
}