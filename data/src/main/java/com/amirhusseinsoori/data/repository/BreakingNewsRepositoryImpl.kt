package com.amirhusseinsoori.data.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.domain.repository.BreakingNewsRepository

import com.amirhusseinsoori.data.source.Remote
import com.amirhusseinsoori.domain.entity.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreakingNewsRepositoryImpl @Inject constructor(private val remote: Remote):
    BreakingNewsRepository {
    override suspend fun getBreakingNews(countryCode: String): Flow<PagingData<Article>> {
        return remote.getArticlesNews(countryCode)
    }


}


