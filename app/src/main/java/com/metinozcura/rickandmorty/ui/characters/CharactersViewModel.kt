package com.metinozcura.rickandmorty.ui.characters

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.metinozcura.rickandmorty.base.BaseViewModel
import com.metinozcura.rickandmorty.data.model.Character
import com.metinozcura.rickandmorty.data.repository.character.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseViewModel() {
    private lateinit var _charactersFlow: Flow<PagingData<Character>>
    val charactersFlow: Flow<PagingData<Character>>
        get() = _charactersFlow

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() = launchPagingAsync({
        characterRepository.getAllCharacters().cachedIn(viewModelScope)
    }, {
        _charactersFlow = it
    })
}