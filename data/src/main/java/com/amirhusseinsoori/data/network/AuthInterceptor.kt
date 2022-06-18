package com.amirhusseinsoori.data.network


import com.amirhusseinsoori.common.Constants.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("apiKey", API_KEY)
            .build()
        val newResponse = chain.request().newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newResponse)
    }
}