package com.amirhusseinsoori.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.amirhusseinsoori.data.db.ArticleDao
import com.amirhusseinsoori.data.mapper.ArticleMapper
import com.amirhusseinsoori.domain.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Local @Inject constructor(
    val db: ArticleDao,
    private val articleMapper: ArticleMapper
) {

    suspend fun insertArticle(article: Article): Long =
        db.upsert(articleMapper.mapFromEntity(article))

    suspend fun deleteArticle(article: Article) = db.deleteArticle(articleMapper.mapFromEntity(article))

    fun getAllArticle(): Flow<List<Article>> = db.getAllArticles().map { articleMapper.mapToEntityList(it) }
}