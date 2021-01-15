package com.metinozcura.rickandmorty.ui.locations

import androidx.fragment.app.viewModels
import com.metinozcura.rickandmorty.R
import com.metinozcura.rickandmorty.base.BaseFragment
import com.metinozcura.rickandmorty.databinding.FragmentLocationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding, LocationsViewModel>() {
    private val locationsViewModel: LocationsViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_locations

    override fun getVM(): LocationsViewModel = locationsViewModel

    override fun bindVM(binding: FragmentLocationsBinding, vm: LocationsViewModel) {
    }
}