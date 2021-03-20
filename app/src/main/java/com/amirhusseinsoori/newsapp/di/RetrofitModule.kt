package com.amirhusseinsoori.newsapp.di

import com.amirhusseinsoori.newsapp.api.NewsAPI
import com.amirhusseinsoori.newsapp.util.Constants.Companion.BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
    }


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit.Builder): NewsAPI {
        return retrofit.build().create(NewsAPI::class.java)
    }
}