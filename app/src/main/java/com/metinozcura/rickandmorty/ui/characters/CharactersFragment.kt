package com.metinozcura.rickandmorty.ui.characters

import androidx.fragment.app.viewModels
import com.metinozcura.rickandmorty.R
import com.metinozcura.rickandmorty.base.BaseFragment
import com.metinozcura.rickandmorty.databinding.FragmentCharactersBinding
import com.metinozcura.rickandmorty.ui.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {
    private val charactersViewModel: CharactersViewModel by viewModels()
    private val characterAdapter = CharacterAdapter()
    override val layoutId: Int
        get() = R.layout.fragment_characters

    override fun getVM(): CharactersViewModel = charactersViewModel

    override fun bindVM(binding: FragmentCharactersBinding, vm: CharactersViewModel) {
        with(binding) {
            rvCharacters.adapter = characterAdapter
            with(vm) {
                launchOnLifecycleScope { charactersFlow.collectLatest { characterAdapter.submitData(it) } }
            }
        }
    }
}