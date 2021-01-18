package com.metinozcura.rickandmorty.data.paging

import androidx.paging.PagingSource
import com.metinozcura.rickandmorty.data.model.Character
import com.metinozcura.rickandmorty.data.service.CharacterApi
import javax.inject.Inject

class CharactersPagingDataSource @Inject constructor(val service: CharacterApi) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val nextPageNumber = params.key ?: 1
        return try {
            val response = service.getAllCharacters(nextPageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.results
            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber.inc()
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}