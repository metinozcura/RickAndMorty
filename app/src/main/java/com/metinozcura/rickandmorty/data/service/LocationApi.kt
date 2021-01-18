package com.metinozcura.rickandmorty.data.service

import com.metinozcura.rickandmorty.data.model.Location
import com.metinozcura.rickandmorty.data.model.PagedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("location/")
    suspend fun getAllLocations(@Query("page") page: Int): Response<PagedResponse<Location>>
}