package com.example.networkcoroutines.network

object MarvelApiFactory {

    private val authInterceptor = Interceptor { chain->

        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("apiKey", Constants.PRIVATE_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build()



    fun retrofit() : Retrofit = Retrofit.Builder()
//        .client(httpClient)
        .baseUrl(Constants.MARVEL_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val marvelApi : MarvelApi = retrofit().create(MarvelApi::class.java)
}