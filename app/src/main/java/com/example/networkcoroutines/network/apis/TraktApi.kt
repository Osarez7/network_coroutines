package com.example.networkcoroutines.network.apis

import com.example.networkcoroutines.network.TrendingTraktMovie
import com.uwetrottmann.trakt5.entities.*
import com.uwetrottmann.trakt5.entities.Movie
import com.uwetrottmann.trakt5.enums.Extended
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TraktApi {

    @GET("movies/popular")
    suspend fun popular(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): List<Movie>

    @GET("movies/trending")
    suspend fun trending(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): List<TrendingTraktMovie>

    @GET("movies/{id}")
    suspend fun summary(
        @Path("id") movieId: String,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Movie


    @GET("movies/{id}/comments")
    suspend fun comments(
        @Path("id") movieId: String,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): List<Comment>

}