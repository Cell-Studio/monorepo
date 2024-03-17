package com.cellstudio.core.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

interface HttpClient {
    fun <T : Any> createService(apiClass: Class<T>): T
}

class HttpClientImpl(
    private val baseUrl: String,
    private val converterFactory: Converter.Factory? = null
) : HttpClient {

    override fun <T : Any> createService(apiClass: Class<T>): T =
        OkHttpClient.Builder()
            .apply {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
            .build()
            .let { okHttpClient ->
                Retrofit.Builder()
                    .client(okHttpClient)
                    .apply {
                        converterFactory?.let {
                            this.addConverterFactory(it)
                        }
                    }
                    .baseUrl(baseUrl)
                    .build()
                    .create(apiClass)
            }
}