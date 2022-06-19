package com.amirhusseinsoori.data.repository

import androidx.paging.PagingData
import com.amirhusseinsoori.data.mapper.mapRemoteToFlowArticleDomain
import com.amirhusseinsoori.domain.repository.SearchNewsRepository

import com.amirhusseinsoori.data.source.Remote
import com.amirhusseinsoori.domain.entity.ArticleDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsRepositoryImpI @Inject constructor(private val remote: Remote): SearchNewsRepository {
    override suspend fun searchArticlesNews(Search: String): Flow<PagingData<ArticleDomain>> {
        return remote.searchArticles(Search).mapRemoteToFlowArticleDomain()
    }
}
