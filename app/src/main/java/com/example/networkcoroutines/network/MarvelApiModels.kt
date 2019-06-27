package com.example.networkcoroutines.network

data class MarvelResponse<T>(
    val code: String,
    val status: String,
    val data: Data<T>
)

data class Character(
    val id: Long = 0,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class Data<T>(
    val results: List<T>
)


data class Comic(
    val id: Int,
    val title: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val extension: String
)

data class Movie(
    val title: String,
    val year: Int,
    var ids: Identifiers
)

data class Show(
    val title: Int,
    val year: Int,
    val ids: Identifiers
)

data class Identifiers(
    val trakt: Long,
    val slug: String,
    val imdb: String,
    val tmdb: Int
)


data class AccessToken(
    var access_token: String? = null,
    var token_type: String? = null,
    var expires_in: Int? = null,
    var refresh_token: String? = null,
    var scope: String? = null
)



