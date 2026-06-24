package com.example.dispositivos_moviles_clases.data.remote.dto.users

data class TypicodeUsersDtoItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)