package com.metinozcura.rickandmorty.data.paging.datasource

import android.net.Uri
import androidx.paging.PagingSource
import com.metinozcura.rickandmorty.data.model.Character
import com.metinozcura.rickandmorty.data.service.CharacterApi

class CharactersPagingDataSource(private val service: CharacterApi) :
    PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getAllCharacters(pageNumber)
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