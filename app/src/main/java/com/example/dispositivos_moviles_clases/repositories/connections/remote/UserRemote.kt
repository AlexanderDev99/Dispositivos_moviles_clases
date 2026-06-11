package com.example.dispositivos_moviles_clases.repositories.connections.remote

import com.example.dispositivos_moviles_clases.data.remote.dto.UserDtoRemote

interface UserRemote {

    fun  getAllUsers(): Result<List<UserDtoRemote>>
    fun getOneUser(user: UserDtoRemote): Result<UserDtoRemote>
    fun saveUser(user: UserDtoRemote): Result<UserDtoRemote>
}