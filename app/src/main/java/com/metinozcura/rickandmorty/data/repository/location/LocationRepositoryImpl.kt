package com.metinozcura.rickandmorty.data.repository.location

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.metinozcura.rickandmorty.data.model.Location
import com.metinozcura.rickandmorty.data.paging.datasource.LocationPagingDataSource
import com.metinozcura.rickandmorty.data.service.LocationApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val service: LocationApi) :
    LocationRepository {
    override fun getAllLocations(): Flow<PagingData<Location>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { LocationPagingDataSource(service) }
    ).flow
}