package com.example.networkcoroutines.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(): MarvelResponse<Character>

    @GET("characters")
    suspend fun getCharactersByName(@Query("nameStartsWith") name: String): MarvelResponse<Character>

    @GET("characters/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId: Long): MarvelResponse<Character>

    @GET("characters/{characterId}/comics")
    suspend fun getComicsById(@Path("characterId") characterId: Long): MarvelResponse<Comic>

    @GET("characters/{characterId}/events")
    suspend fun getEventsById(@Path("characterId") characterId: Long): MarvelResponse<Event>
}
