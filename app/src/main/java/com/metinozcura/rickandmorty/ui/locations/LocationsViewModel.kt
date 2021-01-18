package com.metinozcura.rickandmorty.ui.locations

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.metinozcura.rickandmorty.base.BaseViewModel
import com.metinozcura.rickandmorty.data.model.Location
import com.metinozcura.rickandmorty.data.repository.location.LocationRepository
import kotlinx.coroutines.flow.Flow

class LocationsViewModel @ViewModelInject constructor(
    private val locationRepository: LocationRepository
)  : BaseViewModel() {
    private lateinit var _locationsFlow: Flow<PagingData<Location>>
    val locationsFlow: Flow<PagingData<Location>>
        get() = _locationsFlow

    init {
        getAllLocations()
    }

    private fun getAllLocations() = launchPagingAsync({
        locationRepository.getAllLocations().cachedIn(viewModelScope)
    }, {
        _locationsFlow = it
    })
}