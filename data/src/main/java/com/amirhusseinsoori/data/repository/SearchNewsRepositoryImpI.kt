package com.amirhusseinsoori.data.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.domain.repository.SearchNewsRepository

import com.amirhusseinsoori.data.source.Remote
import com.amirhusseinsoori.domain.entity.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsRepositoryImpI @Inject constructor(val remote: Remote): SearchNewsRepository {

    override suspend fun searchArticlesNews(Search: String): Flow<PagingData<Article>> {
        return remote.searchArticles(Search)
    }


}
