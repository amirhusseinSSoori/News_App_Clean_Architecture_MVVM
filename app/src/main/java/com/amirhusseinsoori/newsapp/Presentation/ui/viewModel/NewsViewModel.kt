package com.amirhusseinsoori.newsapp.Presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amirhusseinsoori.newsapp.domain.usecase.BreakingNewUseCase
import com.amirhusseinsoori.newsapp.data.network.response.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor( val useCase: BreakingNewUseCase): ViewModel()  {



    suspend fun breakingNews(): Flow<PagingData<Article>> {
         return useCase.execute("us").cachedIn(viewModelScope)
    }

}