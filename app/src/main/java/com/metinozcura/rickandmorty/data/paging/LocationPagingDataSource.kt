package com.metinozcura.rickandmorty.data.paging

import android.net.Uri
import androidx.paging.PagingSource
import com.metinozcura.rickandmorty.data.model.Location
import com.metinozcura.rickandmorty.data.service.LocationApi

class LocationPagingDataSource(private val service: LocationApi) :
    PagingSource<Int, Location>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getAllLocations(pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.results

            var nextPageNumber: Int? = null
            if (pagedResponse?.pageInfo?.next != null) {
                val uri = Uri.parse(pagedResponse.pageInfo.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}