package com.amirhusseinsoori.newsapp.domain.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.newsapp.api.response.Article
import kotlinx.coroutines.flow.Flow

interface BreakingNewsRepository {

    suspend fun getBreakingNews(countryCode: String): Flow<PagingData<Article>>
}