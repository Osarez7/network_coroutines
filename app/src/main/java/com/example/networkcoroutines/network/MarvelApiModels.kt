package com.example.networkcoroutines.network

data class MarvelResponse(
    val code: String? = null,
    val status: String? = null,
    val data: Data? = null
)

data class Character(
    val id: Long = 0,
    val name: String? = null,
    val description: String? = null,
    val modified: String? = null,
    val thumbnail: Thumbnail? = null,
    val resourceURI: String? = null,
    val comics: ComicsResult? = null
)

data class Data (
    val results: List<Character>? = null
)

data class ComicsResult(
    val available : Int,
    val returned  : Int,
    val collectionURI: String,
    val items:  List<Comic>
)


data class Comic(
    val resourceURI: String,
    val name: String
)

data class Thumbnail (
    val path: String? = null,
    val extension: String? = null
)