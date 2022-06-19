package com.amirhusseinsoori.domain.usecase


import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.amirhusseinsoori.domain.repository.SavedNewsRepository
import com.amirhusseinsoori.domain.usecase.base.UseCase

import javax.inject.Inject

class DeleteNewsUseCase @Inject constructor(
    private val savedNewsRepository: SavedNewsRepository
): UseCase<ArticleDomain,Unit>() {
    override suspend fun execute(params: ArticleDomain?) {
        savedNewsRepository.deleteArticle(params!!)
    }


}