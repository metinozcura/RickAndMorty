package com.metinozcura.rickandmorty.ui.characters

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.metinozcura.rickandmorty.R
import com.metinozcura.rickandmorty.base.BaseFragment
import com.metinozcura.rickandmorty.data.model.Character
import com.metinozcura.rickandmorty.databinding.FragmentCharactersBinding
import com.metinozcura.rickandmorty.databinding.ItemCharacterBinding
import com.metinozcura.rickandmorty.ui.adapter.CharacterAdapter
import com.metinozcura.rickandmorty.util.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(),
    CharacterAdapter.CharacterClickListener {
    private val charactersViewModel: CharactersViewModel by viewModels()

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    override val layoutId: Int
        get() = R.layout.fragment_characters

    override fun getVM(): CharactersViewModel = charactersViewModel

    override fun bindVM(binding: FragmentCharactersBinding, vm: CharactersViewModel) =
        with(binding) {
            with(characterAdapter) {
                rvCharacters.apply {
                    postponeEnterTransition()
                    viewTreeObserver.addOnPreDrawListener {
                        startPostponedEnterTransition()
                        true
                    }
                }
                rvCharacters.adapter = withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(this),
                    footer = PagingLoadStateAdapter(this)
                )

                swipeRefresh.setOnRefreshListener { refresh() }
                characterClickListener = this@CharactersFragment

                with(vm) {
                    launchOnLifecycleScope {
                        charactersFlow.collectLatest { submitData(it) }
                    }
                    launchOnLifecycleScope {
                        loadStateFlow.collectLatest {
                            swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                        }
                    }
                    launchOnLifecycleScope {
                        loadStateFlow.distinctUntilChangedBy { it.refresh }
                            .filter { it.refresh is LoadState.NotLoading }
                            .collect { rvCharacters.scrollToPosition(0) }
                    }
                }
            }
        }

    override fun onCharacterClicked(binding: ItemCharacterBinding, character: Character) {
        val extras = FragmentNavigatorExtras(
            binding.ivAvatar to "avatar_${character.id}",
            binding.tvName to "name_${character.id}",
            binding.tvStatus to "status_${character.id}"
        )
        findNavController().navigate(
            CharactersFragmentDirections.actionCharactersToCharacterDetail(character),
            extras
        )
    }
}