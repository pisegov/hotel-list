package com.myaxa.data

import coil.intercept.Interceptor
import coil.request.ImageResult

class ImageBaseUrlInterceptor(private val imageBaseUrl: String) : Interceptor {
    override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
        val request = chain.request
        val newRequest = request.newBuilder().data("$imageBaseUrl${request.data}").build()
        return chain.proceed(newRequest)
    }
}