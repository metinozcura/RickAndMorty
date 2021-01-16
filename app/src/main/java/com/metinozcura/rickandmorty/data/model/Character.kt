package com.metinozcura.rickandmorty.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val url: String,
    val origin: NameUrl,
    val location: NameUrl,
    val episode: List<String>
)

enum class Status(val status: String) {
    @SerializedName(value = "Alive", alternate = ["alive"])
    ALIVE("Alive"),

    @SerializedName(value = "Dead", alternate = ["dead"])
    DEAD("Dead"),

    @SerializedName(value = "unknown", alternate = ["Unknown"])
    UNKNOWN("Unknown");

    override fun toString() = status
}