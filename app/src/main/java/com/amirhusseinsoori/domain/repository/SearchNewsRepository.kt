package com.amirhusseinsoori.domain.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.newsapp.api.response.Article
import kotlinx.coroutines.flow.Flow

interface SearchNewsRepository {

    suspend fun searchArticlesNews(Search: String): Flow<PagingData<Article>>
}