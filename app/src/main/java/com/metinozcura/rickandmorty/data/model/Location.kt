package com.metinozcura.rickandmorty.data.model

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residence: List<String>
)