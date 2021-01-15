package com.metinozcura.rickandmorty.data.repository.location

import com.metinozcura.rickandmorty.data.service.LocationApi
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val service: LocationApi) :
    LocationRepository