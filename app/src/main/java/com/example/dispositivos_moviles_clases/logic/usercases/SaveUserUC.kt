package com.example.dispositivos_moviles_clases.logic.usercases

import com.example.dispositivos_moviles_clases.data.remote.dto.UserDtoRemote
import com.example.dispositivos_moviles_clases.repositories.connections.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SaveUserUC (val userRepository: UserRepository){
     suspend fun invoke(user: UserDtoRemote, ): Result<UserDtoRemote> = runCatching {
       return userRepository.saveUser(user)

    }
}