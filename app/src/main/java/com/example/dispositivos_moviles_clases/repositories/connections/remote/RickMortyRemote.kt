package com.example.dispositivos_moviles_clases.repositories.connections.remote

import com.example.dispositivos_moviles_clases.data.remote.dto.rickmorty.RickMortyDtoRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyRemote {

    @GET("character")
    fun getAllCharacters(@Query("page") page: Int): Response<RickMortyDtoRemote>
}