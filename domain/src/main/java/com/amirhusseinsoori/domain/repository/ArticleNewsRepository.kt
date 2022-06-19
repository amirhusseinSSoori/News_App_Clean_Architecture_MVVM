package com.amirhusseinsoori.domain.repository

import com.amirhusseinsoori.domain.entity.ArticleDomain


interface ArticleNewsRepository {
    suspend fun insertArticle(article: ArticleDomain):Long
}