package com.amirhusseinsoori.newsapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amirhusseinsoori.newsapp.api.response.Article
import com.amirhusseinsoori.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor( val repository: NewsRepository): ViewModel()  {



    fun getSource(): LiveData<PagingData<Article>> {
         return repository.getBreakingNews("us").cachedIn(viewModelScope)
    }


}