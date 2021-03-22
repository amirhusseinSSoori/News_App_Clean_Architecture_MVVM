package com.amirhusseinsoori.newsapp.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.domain.repository.BreakingNewsRepository
import com.amirhusseinsoori.newsapp.api.response.Article
import com.amirhusseinsoori.newsapp.source.Remote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreakingNewsRepositoryImpl @Inject constructor(private val remote: Remote):
    BreakingNewsRepository {
    override suspend fun getBreakingNews(countryCode: String): Flow<PagingData<Article>> {
        return remote.getArticlesNews(countryCode)
    }


//     fun getBreakingNews(countryCode: String): Flow<PagingData<Article>> {
//        return remote.getArticlesNews(countryCode)
//    }
}


