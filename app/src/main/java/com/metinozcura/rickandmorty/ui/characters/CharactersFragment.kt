package com.metinozcura.rickandmorty.ui.characters

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.metinozcura.rickandmorty.R
import com.metinozcura.rickandmorty.base.BaseFragment
import com.metinozcura.rickandmorty.databinding.FragmentCharactersBinding
import com.metinozcura.rickandmorty.ui.adapter.CharacterAdapter
import com.metinozcura.rickandmorty.util.adapter.paging.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {
    private val charactersViewModel: CharactersViewModel by viewModels()
    private val characterAdapter = CharacterAdapter()
    override val layoutId: Int
        get() = R.layout.fragment_characters

    override fun getVM(): CharactersViewModel = charactersViewModel

    override fun bindVM(binding: FragmentCharactersBinding, vm: CharactersViewModel) {
        with(binding) {
            swipeRefresh.setOnRefreshListener { characterAdapter.refresh() }
            rvCharacters.adapter = characterAdapter.withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(characterAdapter),
                footer = PagingLoadStateAdapter(characterAdapter)
            )
            with(vm) {
                launchOnLifecycleScope {
                    charactersFlow.collectLatest {
                        characterAdapter.submitData(it)
                    }
                }
                launchOnLifecycleScope {
                    characterAdapter.loadStateFlow.collectLatest {
                        swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                    }
                }
                launchOnLifecycleScope {
                    characterAdapter.loadStateFlow
                        .distinctUntilChangedBy { it.refresh }
                        .filter { it.refresh is LoadState.NotLoading }
                        .collect { rvCharacters.scrollToPosition(0) }
                }
            }
        }
    }
}