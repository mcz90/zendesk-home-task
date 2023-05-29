package com.czyzewski.zendeskhometask.network.interceptor

import network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", BuildConfig.ZENDESK_TOKEN)
        val actualRequest = request.build()
        return chain.proceed(actualRequest)
    }
}