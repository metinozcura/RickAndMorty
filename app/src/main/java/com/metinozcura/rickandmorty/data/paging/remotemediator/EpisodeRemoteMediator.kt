package com.metinozcura.rickandmorty.data.paging.remotemediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.metinozcura.rickandmorty.data.model.Episode

@OptIn(ExperimentalPagingApi::class)
class EpisodeRemoteMediator: RemoteMediator<Int, Episode>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Episode>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}