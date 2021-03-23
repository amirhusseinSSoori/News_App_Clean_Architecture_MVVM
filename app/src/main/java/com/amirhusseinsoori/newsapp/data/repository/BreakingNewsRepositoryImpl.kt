package com.amirhusseinsoori.newsapp.data.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.newsapp.domain.repository.BreakingNewsRepository
import com.amirhusseinsoori.newsapp.data.network.response.Article
import com.amirhusseinsoori.newsapp.data.source.Remote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreakingNewsRepositoryImpl @Inject constructor(private val remote: Remote):
    BreakingNewsRepository {
    override suspend fun getBreakingNews(countryCode: String): Flow<PagingData<Article>> {
        return remote.getArticlesNews(countryCode)
    }


}


