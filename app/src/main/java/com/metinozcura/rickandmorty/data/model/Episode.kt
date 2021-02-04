package com.metinozcura.rickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "episodes")
data class Episode(
    @PrimaryKey val id: Int,
    val name: String,
    @SerializedName("air_date") val airDate: String,
    @SerializedName("code", alternate = ["episode"]) val code: String,
    val characters: List<String>,
    var page: Int?
)