package com.amirhusseinsoori.data.repository

import com.amirhusseinsoori.data.source.Local
import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.amirhusseinsoori.domain.repository.SavedNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedNewsRepositoryImpl @Inject constructor(var local: Local) : SavedNewsRepository {
    override suspend fun deleteArticle(article: ArticleDomain) = local.deleteArticle(article)
    override fun getAllArticles(): Flow<List<ArticleDomain>> = local.getAllArticle()
}