package com.metinozcura.rickandmorty.data.repository.episode

import androidx.paging.PagingData
import com.metinozcura.rickandmorty.data.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getAllEpisodes(): Flow<PagingData<Episode>>
}