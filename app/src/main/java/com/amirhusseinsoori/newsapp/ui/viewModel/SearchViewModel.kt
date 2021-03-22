package com.amirhusseinsoori.newsapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amirhusseinsoori.domain.usecase.SearchNewUseCase
import com.amirhusseinsoori.newsapp.api.response.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(val repository: SearchNewUseCase):ViewModel(){



    suspend fun searchNews(query:String): Flow<PagingData<Article>> {
        return repository.execute(query).cachedIn(viewModelScope)
    }
}