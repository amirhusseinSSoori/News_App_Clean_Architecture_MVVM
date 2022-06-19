package com.amirhusseinsoori.domain.usecase

import androidx.paging.PagingData
import com.amirhusseinsoori.domain.repository.SearchNewsRepository
import com.amirhusseinsoori.domain.usecase.base.UseCase
import com.amirhusseinsoori.domain.entity.ArticleDomain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewUseCase @Inject constructor(
    private val searchNewsRepository: SearchNewsRepository
) : UseCase<String, Flow<PagingData<ArticleDomain>>>() {
    override suspend fun execute(params: String?): Flow<PagingData<ArticleDomain>> {
        return searchNewsRepository.searchArticlesNews(params!!)
    }


}