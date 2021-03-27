package com.amirhusseinsoori.domain.repository


import com.amirhusseinsoori.domain.entity.Article
import kotlinx.coroutines.flow.Flow

interface SavedNewsRepository {

    suspend fun deleteArticle(article: Article)

    fun getAllArticles(): Flow<List<Article>>

}