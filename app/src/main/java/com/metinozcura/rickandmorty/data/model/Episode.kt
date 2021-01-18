package com.metinozcura.rickandmorty.data.model

import com.google.gson.annotations.SerializedName

data class Episode(
    val id: Int,
    val name: String,
    @SerializedName("air_date") val airDate: String,
    @SerializedName("code", alternate = ["episode"]) val code: String,
    val characters: List<String>
)