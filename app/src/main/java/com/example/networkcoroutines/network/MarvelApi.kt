package com.example.networkcoroutines.network

import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {

    @GET("characters")
    suspend fun getCharacters(): MarvelResponse
}