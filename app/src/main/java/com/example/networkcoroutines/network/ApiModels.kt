package com.example.networkcoroutines.network

import com.uwetrottmann.tmdb2.enumerations.Status


data class TrendingTraktMovie(
    val movie: MovieTrakt
)

data class MovieTrakt(
    val title: String,
    val overview: String,
    val ids: MovieIds,
    val genres: List<String>
)

data class MovieIds(
    val trakt: Int,
    val imdb: String,
    val tmdb: Int
)

data class MovieTmdb(
    val id: Int,
    val imdb_id: String,
    val revenue: Double,
    val runtime: Int,
    val tagline: String? = null,
    var backdrop_path: String? = null,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var title: String,
    var vote_average: Double,
    var vote_count: Int? = null

)
