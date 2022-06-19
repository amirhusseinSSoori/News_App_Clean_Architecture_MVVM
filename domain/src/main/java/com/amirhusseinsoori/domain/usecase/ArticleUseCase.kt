package com.amirhusseinsoori.domain.usecase


import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.amirhusseinsoori.domain.repository.ArticleNewsRepository
import com.amirhusseinsoori.domain.usecase.base.UseCase
import javax.inject.Inject

class ArticleUseCase @Inject constructor(
    private val articleNewsRepository: ArticleNewsRepository
) : UseCase<ArticleDomain, Unit>() {
    override suspend fun execute(params: ArticleDomain?) {
        articleNewsRepository.insertArticle(params!!)
    }
}