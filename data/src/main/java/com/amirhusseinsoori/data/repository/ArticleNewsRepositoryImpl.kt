package com.amirhusseinsoori.data.repository

import com.amirhusseinsoori.data.source.Local
import com.amirhusseinsoori.data.network.model.Article
import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.amirhusseinsoori.domain.repository.ArticleNewsRepository
import javax.inject.Inject

class ArticleNewsRepositoryImpl @Inject constructor(val local: Local) : ArticleNewsRepository {
    override suspend fun insertArticle(article: ArticleDomain): Long = local.insertArticle(article)
}

