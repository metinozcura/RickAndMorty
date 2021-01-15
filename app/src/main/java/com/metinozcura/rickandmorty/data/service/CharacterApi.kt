package com.metinozcura.rickandmorty.data.service

import com.metinozcura.rickandmorty.data.model.PagedResponse
import com.metinozcura.rickandmorty.data.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("character/")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<PagedResponse<Character>>

    suspend fun getSingleCharacter(id: Int)

    suspend fun getMultipleCharacters(ids: List<Int>)

    suspend fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    )
}