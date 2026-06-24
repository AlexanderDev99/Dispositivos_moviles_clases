package com.example.dispositivos_moviles_clases.logic.usercases

import com.example.dispositivos_moviles_clases.data.remote.dto.rickmorty.Result
import com.example.dispositivos_moviles_clases.repositories.TypiUsersRepository
import com.example.dispositivos_moviles_clases.repositories.connections.remote.RickMortyRemote
import retrofit2.create

class GetCharacterRickAndMorty {
    suspend fun invoke () : List<Result>?  {
        val conn = TypiUsersRepository.getApiRickMorty()
        val call = conn.create<RickMortyRemote>().getAllCharacters(1)
        if(call.isSuccessful){
            val body = call.body()
            return body?.results
        }else{
            return emptyList()
        }
    }
}