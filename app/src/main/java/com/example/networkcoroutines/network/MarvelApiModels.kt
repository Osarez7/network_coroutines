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
    val modified: String? = null,
    val thumbnail: Thumbnail? = null,
    val resourceURI: String? = null
)

data class Data<T>(
    val results: List<T>
)

data class Comic(
    val id: Int,
    val title: String,
    val description: String,
    val isbn: String,
    val thumbnail: Thumbnail? = null,
    val images: List<Thumbnail>
)

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val start: String,
    val end: String,
    val thumbnail: Thumbnail
)


data class Thumbnail (
    val path: String? = null,
    val extension: String? = null
)