package com.example.dispositivos_moviles_clases.repositories.connections.remote

import com.example.dispositivos_moviles_clases.data.remote.dto.UserDtoRemote

class UserRemoteImpl : UserRemote{

    override fun getAllUsers(): Result<List<UserDtoRemote>> {
        TODO("Not yet implemented")
    }

    override fun getOneUser(user: UserDtoRemote): Result<UserDtoRemote> {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: UserDtoRemote): Result<UserDtoRemote> {
        TODO("Not yet implemented")
    }
}