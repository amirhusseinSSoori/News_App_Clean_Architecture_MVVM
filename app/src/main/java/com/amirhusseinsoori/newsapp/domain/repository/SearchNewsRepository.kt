package com.amirhusseinsoori.newsapp.domain.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.newsapp.data.network.response.Article
import kotlinx.coroutines.flow.Flow

interface SearchNewsRepository {

    suspend fun searchArticlesNews(Search: String): Flow<PagingData<Article>>
}