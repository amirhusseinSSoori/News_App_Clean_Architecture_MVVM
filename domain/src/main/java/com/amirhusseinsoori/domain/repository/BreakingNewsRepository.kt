package com.amirhusseinsoori.domain.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.domain.entity.ArticleDomain

import kotlinx.coroutines.flow.Flow

interface BreakingNewsRepository {
    suspend fun getBreakingNews(countryCode: String): Flow<PagingData<ArticleDomain>>
}