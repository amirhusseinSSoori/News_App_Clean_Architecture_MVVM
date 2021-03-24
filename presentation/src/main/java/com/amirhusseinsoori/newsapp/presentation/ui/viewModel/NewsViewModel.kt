package com.amirhusseinsoori.newsapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amirhusseinsoori.domain.entity.Article
import com.amirhusseinsoori.domain.usecase.BreakingNewUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor( val useCase: BreakingNewUseCase): ViewModel()  {



    suspend fun breakingNews(): Flow<PagingData<Article>> {
         return useCase.execute("us").cachedIn(viewModelScope)
    }

}