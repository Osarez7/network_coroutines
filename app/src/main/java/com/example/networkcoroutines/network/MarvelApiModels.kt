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

data class Data<T> (
    val results: List<T>
)


data class Comic(
    val id: Int,
    val title: String,
    val thumbnail: Thumbnail
)

data class Thumbnail (
    val path: String,
    val extension: String
)