package com.example.networkcoroutines.network

import retrofit2.Call
import retrofit2.http.*


interface MarvelApi {

//    @GET("characters")
//    suspend fun getCharacters(): MarvelResponse<Character>
//
//    @GET("characters")
//    suspend fun getCharactersByName(@Query("nameStartsWith") name: String): MarvelResponse<Character>
//
//    @GET("characters/{characterId}")
//    suspend fun getCharacterById(@Path("characterId") characterId: Long): MarvelResponse<Character>
//
//    @GET("characters/{characterId}/comics")
//    suspend fun getComicsByCharacterId(@Path("characterId") characterId: Long): MarvelResponse<Comic>


    @FormUrlEncoded
    @POST("/auth/token")
    fun exchangeCodeForAccessToken(
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("redirect_uri") redirectUri: String
    ): Call<AccessToken>

    @FormUrlEncoded
    @POST("oauth/token")
    fun refreshAccessToken(
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refreshToken: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("redirect_uri") redirectUri: String
    ): Call<AccessToken>
}
