package com.example.networkcoroutines.network

import android.R.attr.apiKey
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class Trakt_ {
    companion object {
        const val SITE_URL = "https://trakt.tv"
        const val OAUTH2_AUTHORIZATION_URL = SITE_URL + "/oauth/authorize"
        const val redirectUri = "https://trakt_app.com"
        const val apiKey = "9e29fa5f49ac3a1a8a8bc9fee63726f06dbe5441b2d603f88f4162be0044d09c"
        const val clientSecret = "513520deb422ce95916c020ad64b33e687a838c73f8246a9b8a1e1d853daaf31"
        const val accessToken = ""
        const val API_HOST = "api.trakt.tv"


        const val HEADER_AUTHORIZATION = "Authorization"
        const val HEADER_CONTENT_TYPE = "Content-Type"
        const  val CONTENT_TYPE_JSON = "application/json"
        const  val HEADER_TRAKT_API_VERSION = "trakt-api-version"
        const  val HEADER_TRAKT_API_KEY = "trakt-api-key"
        const  val API_VERSION = "2"

        const val tmdbAoiKey = "5b78bcd089c4c12198674274bff54bc4"
    }


    /**
     * Build an OAuth 2.0 authorization URL to obtain an authorization code.
     *
     *
     * Send the user to the URL. Once the user authorized your app, the server will redirect to `redirectUri`
     * with the authorization code and the sent state in the query parameter `code`.
     *
     *
     * Ensure the state matches, then supply the authorization code to [.exchangeCodeForAccessToken] to get an
     * access token.
     *
     * @param state State variable to prevent request forgery attacks.
     */
    fun buildAuthorizationUrl(state: String): String {
        if (redirectUri == null) {
            throw IllegalStateException("redirectUri not provided")
        }

        val authUrl = StringBuilder(OAUTH2_AUTHORIZATION_URL)
        authUrl.append("?").append("response_type=code")
        authUrl.append("&").append("redirect_uri=").append(urlEncode(redirectUri))
        authUrl.append("&").append("state=").append(urlEncode(state))
        authUrl.append("&").append("client_id=").append(urlEncode(apiKey))
        return authUrl.toString()
    }

    private fun urlEncode(content: String): String {
        try {
            // can not use java.nio.charset.StandardCharsets as on Android only available since API 19
            return URLEncoder.encode(content, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            throw UnsupportedOperationException(e)
        }

    }
}
