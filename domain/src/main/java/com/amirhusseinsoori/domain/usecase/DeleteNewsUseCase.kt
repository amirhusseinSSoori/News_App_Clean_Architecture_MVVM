package com.amirhusseinsoori.domain.usecase

import com.amirhusseinsoori.domain.entity.Article
import com.amirhusseinsoori.domain.repository.SavedNewsRepository
import com.amirhusseinsoori.domain.usecase.base.UseCase

import javax.inject.Inject

class DeleteNewsUseCase @Inject constructor(
    private val savedNewsRepository: SavedNewsRepository
): UseCase<Article,Unit>() {
    override suspend fun execute(params: Article?) {
        savedNewsRepository.deleteArticle(params!!)
    }


}