package com.example.dispositivos_moviles_clases.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TypiUsersRepository {

    private const val TYPI_BASE = "https://jsonplaceholder.typicode.com/"
    private const val RICKMORTYBASE = "https://jsonplaceholder.typicode.com/"
    // Coneccion a la API
    private fun baseURL(base: String): Retrofit {
        val baseConeccion = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return baseConeccion
    }

    fun getApiUsersTypi(): Retrofit {
        return baseURL(TYPI_BASE)
    }

    fun getApiRickMorty(): Retrofit{
        return baseURL(TYPI_BASE)
    }



}