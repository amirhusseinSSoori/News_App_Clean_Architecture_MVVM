package com.amirhusseinsoori.data.repository

import com.amirhusseinsoori.data.source.Local
import com.amirhusseinsoori.domain.entity.Article
import com.amirhusseinsoori.domain.repository.ArticleNewsRepository
import javax.inject.Inject

class ArticleNewsRepositoryImpl @Inject constructor(val local: Local) : ArticleNewsRepository {
    override suspend fun insertArticle(article: Article): Long = local.insertArticle(article)
}

