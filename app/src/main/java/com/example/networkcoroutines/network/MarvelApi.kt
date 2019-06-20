package com.example.networkcoroutines.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(): MarvelResponse

    @GET("characters/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId: Long): MarvelResponse
}
