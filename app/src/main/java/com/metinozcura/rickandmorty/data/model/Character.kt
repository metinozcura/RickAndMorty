package com.metinozcura.rickandmorty.data.model

data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender,
    val image: String,
    val url: String,
    val origin: NameUrl,
    val location: NameUrl,
    val episode: List<String>
)