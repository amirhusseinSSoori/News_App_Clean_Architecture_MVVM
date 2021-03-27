package com.amirhusseinsoori.domain.usecase

import androidx.lifecycle.LiveData
import com.amirhusseinsoori.domain.entity.Article
import com.amirhusseinsoori.domain.repository.SavedNewsRepository
import com.amirhusseinsoori.domain.usecase.base.UseCaseImmediate
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class AllNewsUseCase @Inject constructor(
    private val savedNewsRepository: SavedNewsRepository
): UseCaseImmediate<Flow<List<Article>>>() {

    override fun buildUseCaseImmediate(): Flow<List<Article>> {
        return savedNewsRepository.getAllArticles()
    }
}