package com.amirhusseinsoori.domain.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.domain.entity.ArticleDomain

import kotlinx.coroutines.flow.Flow

interface SearchNewsRepository {

    suspend fun searchArticlesNews(Search: String): Flow<PagingData<ArticleDomain>>
}