package com.example.networkcoroutines.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun getCharacters(): Call<MarvelResponse<Character>>

    @GET("characters")
    fun getCharactersByName(@Query("nameStartsWith") name: String): Call<MarvelResponse<Character>>

    @GET("characters/{characterId}")
    fun getCharacterById(@Path("characterId") characterId: Long): Call<MarvelResponse<Character>>

    @GET("characters/{characterId}/comics")
    fun getComicsByCharacterId(@Path("characterId") characterId: Long): Call<MarvelResponse<Comic>>
}
