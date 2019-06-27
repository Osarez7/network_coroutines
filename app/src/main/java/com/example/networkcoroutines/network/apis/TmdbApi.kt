package com.example.networkcoroutines.network.apis

import com.example.networkcoroutines.network.MovieTmdb
import com.uwetrottmann.tmdb2.entities.Images
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/{movie_id}/images")
    suspend fun images(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): Images

    @GET("movie/{movie_id}")
    suspend fun summary(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): MovieTmdb
}