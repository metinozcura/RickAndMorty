package com.metinozcura.rickandmorty.ui.episodes

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.metinozcura.rickandmorty.R
import com.metinozcura.rickandmorty.base.BaseFragment
import com.metinozcura.rickandmorty.databinding.FragmentEpisodesBinding
import com.metinozcura.rickandmorty.ui.adapter.EpisodeAdapter
import com.metinozcura.rickandmorty.util.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding, EpisodesViewModel>() {
    private val episodesViewModel: EpisodesViewModel by viewModels()

    @Inject
    lateinit var episodeAdapter: EpisodeAdapter

    override val layoutId: Int
        get() = R.layout.fragment_episodes

    override fun getVM(): EpisodesViewModel = episodesViewModel

    override fun bindVM(binding: FragmentEpisodesBinding, vm: EpisodesViewModel) = with(binding) {
        with(episodeAdapter) {
            swipeRefresh.setOnRefreshListener { refresh() }
            rvEpisodes.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
            with(vm) {
                launchOnLifecycleScope {
                    episodesFlow.collectLatest { submitData(it) }
                }
                launchOnLifecycleScope {
                    loadStateFlow.collectLatest {
                        swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                    }
                }
                launchOnLifecycleScope {
                    loadStateFlow.distinctUntilChangedBy { it.refresh }
                        .filter { it.refresh is LoadState.NotLoading }
                        .collect { rvEpisodes.scrollToPosition(0) }
                }
            }
        }
    }
}