package com.example.dispositivos_moviles_clases.repositories.connections.remote

import com.example.dispositivos_moviles_clases.data.remote.dto.UserDtoRemote

interface UserRemote {

    suspend fun  getAllUsers(): Result<List<UserDtoRemote>>
    suspend fun getOneUser(user: UserDtoRemote): Result<UserDtoRemote>
    suspend fun saveUser(user: UserDtoRemote): Result<UserDtoRemote>
}