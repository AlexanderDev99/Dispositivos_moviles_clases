package com.example.dispositivos_moviles_clases.logic.usercases

import com.example.dispositivos_moviles_clases.data.remote.dto.UserDtoRemote
import com.example.dispositivos_moviles_clases.repositories.connections.UserRepository

class GetAllUsersUC (val userRepository: UserRepository){
    suspend fun invoke() : Result<List<UserDtoRemote>>{
        return userRepository.getAllUsers()
    }
}