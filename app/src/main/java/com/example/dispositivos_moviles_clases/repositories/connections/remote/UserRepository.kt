package com.example.dispositivos_moviles_clases.repositories.connections

import com.example.dispositivos_moviles_clases.data.remote.dto.UserDtoRemote
import com.example.dispositivos_moviles_clases.repositories.connections.remote.UserRemote
import com.example.dispositivos_moviles_clases.repositories.connections.remote.UserRemoteImpl

class UserRepository (val userRemoteImpl: UserRemote) {

    suspend fun saveUser(user : UserDtoRemote) : Result<UserDtoRemote> {
        return userRemoteImpl.saveUser(user)
    }

    suspend fun getAllUsers() : Result<List<UserDtoRemote>> {
       return userRemoteImpl.getAllUsers()
    }
}