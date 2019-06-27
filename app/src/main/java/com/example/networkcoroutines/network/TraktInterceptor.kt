package com.example.networkcoroutines.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Request
import java.io.IOException


class TraktInterceptor: Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return handleIntercept(chain, Trakt_.apiKey, Trakt_.accessToken)
    }

    /**
     * If the host matches [Trakt_.API_HOST] adds a header for the current [Trakt_.API_VERSION], [ ][Trakt_.HEADER_TRAKT_API_KEY] with the given api key, [Trakt_.HEADER_CONTENT_TYPE] and if not present an
     * Authorization header using the given access token.
     */
    @Throws(IOException::class)
    fun handleIntercept(
        chain: Interceptor.Chain, apiKey: String,
        accessToken: String
    ): Response {
        val request = chain.request()
        if (!Trakt_.API_HOST.equals(request.url().host())) {
            // do not intercept requests for other hosts
            // this allows the interceptor to be used on a shared okhttp client
            return chain.proceed(request)
        }

        val builder = request.newBuilder()

        // set required content type, api key and request specific API version
        builder.header(Trakt_.HEADER_CONTENT_TYPE, Trakt_.CONTENT_TYPE_JSON)
        builder.header(Trakt_.HEADER_TRAKT_API_KEY, apiKey)
        builder.header(Trakt_.HEADER_TRAKT_API_VERSION, Trakt_.API_VERSION)

        // add authorization header
        if (hasNoAuthorizationHeader(request) && accessTokenIsNotEmpty(accessToken)) {
            builder.header(Trakt_.HEADER_AUTHORIZATION, "Bearer $accessToken")
        }
        return chain.proceed(builder.build())
    }

    private fun hasNoAuthorizationHeader(request: Request): Boolean {
        return request.header(Trakt_.HEADER_AUTHORIZATION) == null
    }

    private fun accessTokenIsNotEmpty(accessToken: String?): Boolean {
        return accessToken != null && accessToken.length != 0
    }
}