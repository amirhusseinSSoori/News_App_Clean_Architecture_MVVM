package com.amirhusseinsoori.newsapp.data.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.newsapp.domain.repository.SearchNewsRepository

import com.amirhusseinsoori.newsapp.data.source.Remote
import com.amirhusseinsoori.newsapp.presentation.entity.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsRepositoryImpI @Inject constructor(val remote: Remote):SearchNewsRepository {

    override suspend fun searchArticlesNews(Search: String): Flow<PagingData<Article>> {
        return remote.searchArticles(Search)
    }


}
