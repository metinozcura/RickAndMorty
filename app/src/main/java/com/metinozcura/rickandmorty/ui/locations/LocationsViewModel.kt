package com.metinozcura.rickandmorty.ui.locations

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.metinozcura.rickandmorty.base.BaseViewModel
import com.metinozcura.rickandmorty.data.model.Location
import com.metinozcura.rickandmorty.data.repository.location.LocationRepository
import com.metinozcura.rickandmorty.ui.locations.model.LocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : BaseViewModel() {
    private lateinit var _locationsFlow: Flow<PagingData<LocationModel>>
    val locationsFlow: Flow<PagingData<LocationModel>>
        get() = _locationsFlow

    init {
        getAllLocations()
    }

    private fun getAllLocations() = launchPagingAsync({
        locationRepository.getAllLocations()
    }, { flow ->
        _locationsFlow = flow.map { pagingData: PagingData<Location> ->
            pagingData.map { location ->
                LocationModel.LocationData(location)
            }.insertSeparators<LocationModel.LocationData, LocationModel> { before, after ->
                when {
                    before == null -> null
                    after == null -> null
                    else -> LocationModel.LocationSeparator("Separator: $before-$after")
                }
            }
        }.cachedIn(viewModelScope)
    })
}