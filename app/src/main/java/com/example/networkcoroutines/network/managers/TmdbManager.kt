package com.example.networkcoroutines.network.managers

import com.example.networkcoroutines.network.apis.TmdbApi
import com.uwetrottmann.tmdb2.Tmdb

class TmdbManager(apiKey: String): Tmdb(apiKey){
    val api = retrofit.create(TmdbApi::class.java)
}