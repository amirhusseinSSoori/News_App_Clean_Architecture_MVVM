package com.amirhusseinsoori.domain.usecase

import androidx.paging.PagingData
import com.amirhusseinsoori.domain.repository.BreakingNewsRepository
import com.amirhusseinsoori.domain.usecase.base.UseCase
import com.amirhusseinsoori.domain.entity.Article

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreakingNewUseCase @Inject constructor(
    private val breakingNewsRepository: BreakingNewsRepository
) : UseCase<String, Flow<PagingData<Article>>>() {


    override suspend fun execute(params: String?): Flow<PagingData<Article>> {
        return breakingNewsRepository.getBreakingNews(params!!)
    }


}