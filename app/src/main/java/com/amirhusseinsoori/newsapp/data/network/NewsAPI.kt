package com.amirhusseinsoori.newsapp.data.network


import com.amirhusseinsoori.newsapp.presentation.entity.NewsResponse
import com.amirhusseinsoori.newsapp.presentation.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {



    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String,
        @Query("page") page: Int,
        @Query("pageSize") num: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("pageSize") num: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}