package com.metinozcura.rickandmorty.data.model

import com.google.gson.annotations.SerializedName

enum class Gender(val gender: String) {
    @SerializedName(value = "Female", alternate = ["female"])
    FEMALE("Female"),

    @SerializedName(value = "Male", alternate = ["male"])
    MALE("Male"),

    @SerializedName(value = "Genderless", alternate = ["genderless"])
    GENDERLESS("Genderless"),

    @SerializedName(value = "unknown", alternate = ["Unknown"])
    UNKNOWN("Unknown");

    override fun toString() = gender
}