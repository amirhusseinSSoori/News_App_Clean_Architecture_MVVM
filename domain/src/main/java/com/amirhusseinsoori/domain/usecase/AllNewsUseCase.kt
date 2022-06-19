package com.amirhusseinsoori.domain.usecase


import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.amirhusseinsoori.domain.repository.SavedNewsRepository
import com.amirhusseinsoori.domain.usecase.base.UseCaseImmediate
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class AllNewsUseCase @Inject constructor(
    private val savedNewsRepository: SavedNewsRepository
): UseCaseImmediate<Flow<List<ArticleDomain>>>() {

    override fun buildUseCaseImmediate(): Flow<List<ArticleDomain>> {
        return savedNewsRepository.getAllArticles()
    }
}