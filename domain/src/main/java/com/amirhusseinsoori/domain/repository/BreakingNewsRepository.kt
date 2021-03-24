package com.amirhusseinsoori.domain.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.domain.entity.Article

import kotlinx.coroutines.flow.Flow

interface BreakingNewsRepository {

    suspend fun getBreakingNews(countryCode: String): Flow<PagingData<Article>>
}