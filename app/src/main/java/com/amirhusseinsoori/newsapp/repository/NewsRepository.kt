package com.amirhusseinsoori.newsapp.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.newsapp.api.response.Article
import com.amirhusseinsoori.newsapp.source.Remote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val remote: Remote) {



    suspend fun getBreakingNews(countryCode: String): Flow<PagingData<Article>> {
      return remote.getArticlesNews(countryCode)
    }


    fun searchArticlesNews(query :String): Flow<PagingData<Article>> {
        return remote.searchArticles(query)
    }
}