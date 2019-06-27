package com.example.networkcoroutines.view.models


data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String
)


data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val popularity: Double,
    val revenue: Double,
    val runtime: Int,
    val vote_average: Double
)