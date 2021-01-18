package com.metinozcura.rickandmorty.data.service

import com.metinozcura.rickandmorty.data.model.Episode
import com.metinozcura.rickandmorty.data.model.PagedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApi {
    @GET("episode/")
    suspend fun getAllEpisodes(@Query("page") page: Int): Response<PagedResponse<Episode>>
}