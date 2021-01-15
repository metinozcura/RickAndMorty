package com.metinozcura.rickandmorty.data.repository.episode

import com.metinozcura.rickandmorty.data.service.EpisodeApi
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(private val service: EpisodeApi) :
    EpisodeRepository {
}