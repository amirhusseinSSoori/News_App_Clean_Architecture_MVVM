package com.amirhusseinsoori.data.di


import com.amirhusseinsoori.data.BuildConfig.DEBUG
import com.amirhusseinsoori.data.network.AuthInterceptor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {


    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return loggingInterceptor
    }


    @Provides
    fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor,authInterceptor: AuthInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .readTimeout(500 , TimeUnit.SECONDS)
            .writeTimeout(500 , TimeUnit.SECONDS)
            .connectTimeout(500 , TimeUnit.SECONDS)
            .build()
    }




  


    @Provides
    @Singleton
    fun provideClientService(
        okHttpClient: OkHttpClient,
    ): HttpClient {
        return HttpClient(OkHttp) {
            // Logging
            install(Logging) {
                level = LogLevel.ALL
            }
            // GSON
            install(ContentNegotiation) {
                gson()
            }
            engine {
                // this: OkHttpConfig
                config {
                    // this: OkHttpClient.Builder
                    followRedirects(true)
                    // ...
                }
                preconfigured = okHttpClient
            }

            // Timeout
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }
            // Apply to all requests
            defaultRequest {

                // Content Type
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}