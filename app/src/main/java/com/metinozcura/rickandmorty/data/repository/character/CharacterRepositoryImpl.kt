package com.metinozcura.rickandmorty.data.repository.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.metinozcura.rickandmorty.data.model.Character
import com.metinozcura.rickandmorty.data.paging.CharactersPagingDataSource
import com.metinozcura.rickandmorty.data.service.CharacterApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterApi,
    private val charactersPagingDataSource: CharactersPagingDataSource
) : CharacterRepository {

    override suspend fun getAllCharacters(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { charactersPagingDataSource }
    ).flow

    override suspend fun getSingleCharacter(id: Int) = service.getSingleCharacter(id)

    override suspend fun getMultipleCharacters(ids: List<Int>) = service.getMultipleCharacters(ids)

    override suspend fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ) = service.filterCharacters(name, status, species, type, gender)
}