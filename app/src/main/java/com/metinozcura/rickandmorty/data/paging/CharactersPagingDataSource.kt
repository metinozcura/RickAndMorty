package com.metinozcura.rickandmorty.data.paging

import android.net.Uri
import androidx.paging.PagingSource
import com.metinozcura.rickandmorty.data.model.Character
import com.metinozcura.rickandmorty.data.service.CharacterApi
import javax.inject.Inject

class CharactersPagingDataSource @Inject constructor(val service: CharacterApi) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getAllCharacters(pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.results

            val uri = Uri.parse(pagedResponse?.pageInfo?.next)
            val nextPageNumber = uri.getQueryParameter("page")?.toInt()

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