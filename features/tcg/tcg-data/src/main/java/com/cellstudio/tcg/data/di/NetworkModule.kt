package com.cellstudio.tcg.data.di

import com.cellstudio.core.network.HttpClientImpl
import com.cellstudio.tcg.data.apis.TcgApi
import com.cellstudio.tcg.data.repositories.TcgRepositoryImpl
import com.cellstudio.tcg.domain.repositories.TcgRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {
    @Provides
    @Singleton
    fun provideTcgApi(): TcgApi {
        val json = Json { ignoreUnknownKeys = true }
        return HttpClientImpl(
            "https://api.pokemontcg.io",
            json.asConverterFactory("application/json".toMediaType())
        ).createService(TcgApi::class.java)
    }
}

