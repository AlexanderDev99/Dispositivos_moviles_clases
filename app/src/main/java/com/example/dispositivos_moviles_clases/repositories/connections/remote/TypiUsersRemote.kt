package com.example.dispositivos_moviles_clases.repositories.connections.remote

import com.example.dispositivos_moviles_clases.data.remote.dto.users.TypicodeUsersDtoItem
import retrofit2.Response
import retrofit2.http.GET

interface TypiUsersRemote {

    @GET("users")
    suspend fun getAllUsersTypi() : Response<List<TypicodeUsersDtoItem>>
}