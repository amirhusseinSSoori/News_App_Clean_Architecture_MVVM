package com.amirhusseinsoori.domain.usecase

import com.amirhusseinsoori.domain.entity.Article
import com.amirhusseinsoori.domain.repository.ArticleNewsRepository
import com.amirhusseinsoori.domain.usecase.base.UseCase
import javax.inject.Inject

class ArticleUseCase @Inject constructor(

    private val articleNewsRepository: ArticleNewsRepository
) : UseCase<Article, Unit>() {
    override suspend fun execute(params: Article?) {
        articleNewsRepository.insertArticle(params!!)
    }
}