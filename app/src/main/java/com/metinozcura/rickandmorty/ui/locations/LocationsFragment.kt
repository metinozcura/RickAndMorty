package com.metinozcura.rickandmorty.ui.locations

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.metinozcura.rickandmorty.R
import com.metinozcura.rickandmorty.base.BaseFragment
import com.metinozcura.rickandmorty.databinding.FragmentLocationsBinding
import com.metinozcura.rickandmorty.ui.adapter.LocationAdapter
import com.metinozcura.rickandmorty.util.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding, LocationsViewModel>() {
    private val locationsViewModel: LocationsViewModel by viewModels()

    @Inject
    lateinit var locationAdapter: LocationAdapter

    override val layoutId: Int
        get() = R.layout.fragment_locations

    override fun getVM(): LocationsViewModel = locationsViewModel

    override fun bindVM(binding: FragmentLocationsBinding, vm: LocationsViewModel) = with(binding) {
        with(locationAdapter) {
            swipeRefresh.setOnRefreshListener { refresh() }
            rvLocations.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
            with(vm) {
                launchOnLifecycleScope {
                    locationsFlow.collectLatest { submitData(it) }
                }
                launchOnLifecycleScope {
                    loadStateFlow.collectLatest {
                        swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                    }
                }
                launchOnLifecycleScope {
                    loadStateFlow.distinctUntilChangedBy { it.refresh }
                        .filter { it.refresh is LoadState.NotLoading }
                        .collect { rvLocations.scrollToPosition(0) }
                }
            }
        }
    }
}