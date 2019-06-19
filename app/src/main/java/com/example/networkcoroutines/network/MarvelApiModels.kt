package com.example.networkcoroutines.network

data class MarvelResponse(
    var code: String? = null,
    var status: String? = null,
    var data: Data? = null
)

data class Character(
    var id: Long = 0,
    var name: String? = null,
    var description: String? = null,
    var modified: String? = null,
    var thumbnail: Thumbnail? = null,
    var resourceURI: String? = null
)


data class Data (
    var results: List<Character>? = null
)


data class Thumbnail (
    var path: String? = null,
    var extension: String? = null
)
