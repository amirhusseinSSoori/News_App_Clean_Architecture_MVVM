package com.amirhusseinsoori.domain.usecase

import androidx.paging.PagingData
import com.amirhusseinsoori.domain.repository.BreakingNewsRepository
import com.amirhusseinsoori.domain.usecase.base.UseCase
import com.amirhusseinsoori.domain.entity.ArticleDomain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreakingNewUseCase @Inject constructor(
    private val breakingNewsRepository: BreakingNewsRepository
) : UseCase<String, Flow<PagingData<ArticleDomain>>>() {
    override suspend fun execute(params: String?): Flow<PagingData<ArticleDomain>> {
        return breakingNewsRepository.getBreakingNews(params!!)
    }
}