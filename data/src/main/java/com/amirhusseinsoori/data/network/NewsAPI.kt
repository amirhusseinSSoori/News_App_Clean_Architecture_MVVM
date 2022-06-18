package com.amirhusseinsoori.data.network



import com.amirhusseinsoori.common.Constants.Companion.API_KEY
import com.amirhusseinsoori.domain.entity.NewsResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {



    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String,
        @Query("page") page: Int,
        @Query("pageSize") num: Int,
        ): NewsResponse

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("pageSize") num: Int,
        ): NewsResponse
}