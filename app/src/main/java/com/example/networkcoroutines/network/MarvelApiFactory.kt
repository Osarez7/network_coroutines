package com.example.networkcoroutines.network

import com.example.networkcoroutines.common.Constants
import com.example.networkcoroutines.common.toMD5
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object MarvelApiFactory {

    private val credentialsInterceptor = Interceptor { chain->

        val timestamp = Date().time.toString()
        val hash = (timestamp + Constants.PRIVATE_KEY + Constants.PUBLIC_KEY).toMD5()

        val newRequest = chain.request()
            .newBuilder()
            .addHeader("Content-type", "application/json")
            .addHeader("trakt-api-key", "9e29fa5f49ac3a1a8a8bc9fee63726f06dbe5441b2d603f88f4162be0044d09c")
            .addHeader("trakt-api-version", "2")
            .build()

        chain.proceed(newRequest)
    }

    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(credentialsInterceptor)
        .build()



    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(Constants.MARVEL_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val marvelApi : MarvelApi = retrofit().create(MarvelApi::class.java)
}