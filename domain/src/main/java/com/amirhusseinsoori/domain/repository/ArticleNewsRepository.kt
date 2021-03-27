package com.amirhusseinsoori.domain.repository

import com.amirhusseinsoori.domain.entity.Article

interface ArticleNewsRepository {


    suspend fun insertArticle(article: Article):Long
}