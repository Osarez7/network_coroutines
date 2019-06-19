package com.example.networkcoroutines.network

import com.example.networkcoroutines.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MarvelApiClient {


    private var  retrofit: Retrofit? = null

    val client: Retrofit?
        get() {

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Constants.MARVEL_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit
        }
}