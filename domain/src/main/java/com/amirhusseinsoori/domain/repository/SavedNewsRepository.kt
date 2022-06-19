package com.amirhusseinsoori.domain.repository



import com.amirhusseinsoori.domain.entity.ArticleDomain
import kotlinx.coroutines.flow.Flow

interface SavedNewsRepository {

    suspend fun deleteArticle(article: ArticleDomain)

    fun getAllArticles(): Flow<List<ArticleDomain>>

}