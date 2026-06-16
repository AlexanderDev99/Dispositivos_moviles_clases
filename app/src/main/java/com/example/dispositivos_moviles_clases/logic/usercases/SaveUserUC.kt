package com.example.dispositivos_moviles_clases.logic.usercases

import com.example.dispositivos_moviles_clases.data.remote.dto.UserDtoRemote
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SaveUserUC {

     suspend fun saveUser(user: UserDtoRemote, db: FirebaseFirestore): Result<UserDtoRemote> = runCatching {
        // 3. El runCatching debe envolver toda la operación asíncrona
        db.collection("users")
            .add(user)
            .await()
        user
    }
}