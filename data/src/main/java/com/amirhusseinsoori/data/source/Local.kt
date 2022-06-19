package com.amirhusseinsoori.data.source

import com.amirhusseinsoori.data.db.ArticleDao
import com.amirhusseinsoori.data.db.entity.ArticleEntity
import com.amirhusseinsoori.data.mapper.mapLocalToArticleDomain
import com.amirhusseinsoori.data.mapper.mapLocalToListArticleEntity
import com.amirhusseinsoori.data.network.model.Article
import com.amirhusseinsoori.domain.entity.ArticleDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Local @Inject constructor(
    val db: ArticleDao
) {
    suspend fun insertArticle(article: ArticleDomain): Long =
        db.upsert(article.mapLocalToArticleDomain())

    suspend fun deleteArticle(article: ArticleDomain) =
        db.deleteArticle(article.mapLocalToArticleDomain())

    fun getAllArticle(): Flow<List<ArticleDomain>> =
        db.getAllArticles().map { it.mapLocalToListArticleEntity() }
}