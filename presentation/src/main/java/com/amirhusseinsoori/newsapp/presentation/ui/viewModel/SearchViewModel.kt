package com.amirhusseinsoori.newsapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amirhusseinsoori.domain.usecase.SearchNewUseCase
import com.amirhusseinsoori.domain.entity.ArticleDomain

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchNewUseCase) : ViewModel() {
    suspend fun searchNews(query: String): Flow<PagingData<ArticleDomain>> {
        return repository.execute(query).cachedIn(viewModelScope)
    }
}

