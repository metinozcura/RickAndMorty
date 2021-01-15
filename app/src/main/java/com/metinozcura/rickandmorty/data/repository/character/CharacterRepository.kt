package com.metinozcura.rickandmorty.data.repository.character

import androidx.paging.PagingData
import com.metinozcura.rickandmorty.data.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getAllCharacters(): Flow<PagingData<Character>>
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