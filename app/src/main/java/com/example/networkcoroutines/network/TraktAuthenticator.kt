package com.example.networkcoroutines.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TraktAuthenticator: Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

       val accessTokenResponse = MarvelApiFactory.marvelApi.refreshAccessToken("refresh_token",
            "",  Trakt_.apiKey,
           Trakt_.clientSecret, Trakt_.redirectUri).execute()


        val accessToken =
        MarvelApiFactory.marvelApi.exchangeCodeForAccessToken("authorization_code",
            "", Trakt_.apiKey,
            Trakt_.clientSecret, "https://trakt_app.com").execute()
        // retry request
        return response.request().newBuilder()
            .header(Trakt_.HEADER_AUTHORIZATION, "Bearer" + " " + accessTokenResponse.body()?.access_token)
            .build();
    }
}