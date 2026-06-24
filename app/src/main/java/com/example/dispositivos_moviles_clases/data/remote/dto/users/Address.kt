package com.example.dispositivos_moviles_clases.data.remote.dto.users

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)