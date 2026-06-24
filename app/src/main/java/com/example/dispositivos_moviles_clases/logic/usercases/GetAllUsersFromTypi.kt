package com.example.dispositivos_moviles_clases.logic.usercases

import com.example.dispositivos_moviles_clases.data.remote.dto.users.TypicodeUsersDtoItem
import com.example.dispositivos_moviles_clases.repositories.TypiUsersRepository
import com.example.dispositivos_moviles_clases.repositories.connections.remote.TypiUsersRemote
import retrofit2.create


class GetAllUsersFromTypi {

    suspend fun invoke(): List<TypicodeUsersDtoItem> {
        val baseApi = TypiUsersRepository.getApiUsersTypi()
        val call = baseApi.create<TypiUsersRemote>().getAllUsersTypi()

        return if (call.isSuccessful) {
            call.body() ?: emptyList()
        } else {
            emptyList()
        }
    }
}
