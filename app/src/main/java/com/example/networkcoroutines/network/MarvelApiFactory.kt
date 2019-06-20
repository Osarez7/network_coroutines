package com.example.networkcoroutines.network

import com.example.networkcoroutines.common.Constants
import com.example.networkcoroutines.common.toMD5
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object MarvelApiFactory {

    private val credentailsInterceptor = Interceptor { chain->

        val timestamp = Date().time.toString()
        val hash = (timestamp + Constants.PRIVATE_KEY + Constants.PUBLIC_KEY).toMD5()

        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("apikey", Constants.PUBLIC_KEY)
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("hash", hash)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(credentailsInterceptor)
        .build()



    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(Constants.MARVEL_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val marvelApi : MarvelApi = retrofit().create(MarvelApi::class.java)
}