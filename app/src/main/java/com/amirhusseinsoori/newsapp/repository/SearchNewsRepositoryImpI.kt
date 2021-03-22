package com.amirhusseinsoori.newsapp.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.newsapp.domain.repository.SearchNewsRepository
import com.amirhusseinsoori.newsapp.api.response.Article
import com.amirhusseinsoori.newsapp.source.Remote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsRepositoryImpI @Inject constructor(val remote: Remote):SearchNewsRepository {
//    fun searchArticlesNews(query: String): Flow<PagingData<Article>> {
//        return remote.searchArticles(query)
//    }

    override suspend fun searchArticlesNews(Search: String): Flow<PagingData<Article>> {
        return remote.searchArticles(Search)
    }


}
