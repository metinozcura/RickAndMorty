package com.metinozcura.rickandmorty.ui.locations.model

import com.metinozcura.rickandmorty.data.model.Location

sealed class LocationModel {
    class LocationData(
        val id: Int,
        val name: String,
        val type: String,
        val dimension: String,
        val residence: List<String>?
    ) : LocationModel() {
        constructor(location: Location) : this(
            location.id,
            location.name,
            location.type,
            location.dimension,
            location.residence
        )
    }
    class LocationSeparator(val tag: String) : LocationModel()
}