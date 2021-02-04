package com.metinozcura.rickandmorty.data.paging.remotemediator

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.metinozcura.rickandmorty.data.db.AppDB
import com.metinozcura.rickandmorty.data.model.Episode
import com.metinozcura.rickandmorty.data.model.PageKey
import com.metinozcura.rickandmorty.data.service.EpisodeApi

@OptIn(ExperimentalPagingApi::class)
class EpisodeRemoteMediator(val service: EpisodeApi, val db: AppDB) : RemoteMediator<Int, Episode>() {
    private val episodeDao = db.episodeDao()
    private val keyDao = db.pageKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Episode>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    val remoteKey: PageKey? = db.withTransaction {
                        if (lastItem?.id != null) {
                            keyDao.getNextPageKey(lastItem.id)
                        } else null
                    }

                    if (remoteKey?.nextPageUrl == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }

                    val uri = Uri.parse(remoteKey.nextPageUrl)
                    val nextPageQuery = uri.getQueryParameter("page")
                    nextPageQuery?.toInt()
                }
            }

            val response = service.getAllEpisodes(loadKey ?: 1)
            val resBody = response.body()
            val pageInfo = resBody?.pageInfo
            val episodes = resBody?.results
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    episodeDao.clearAll()
                    keyDao.clearAll()
                }
                episodes?.forEach {
                    it.page = loadKey
                    keyDao.insertOrReplace(PageKey(it.id, pageInfo?.next))
                }
                episodes?.let { episodeDao.insertAll(it) }

            }

            MediatorResult.Success(
                endOfPaginationReached = resBody?.pageInfo?.next == null
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}