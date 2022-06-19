package com.amirhusseinsoori.newsapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.amirhusseinsoori.domain.usecase.BreakingNewUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor( val useCase: BreakingNewUseCase): ViewModel()  {


    private val state= MutableStateFlow<PagingData<ArticleDomain>>(PagingData.empty())
    var _state=state.asStateFlow()



    fun setUpEvent(){
        viewModelScope.launch {
            useCase.execute("us").cachedIn(viewModelScope).collectLatest {
                state.value =it
            }
        }
    }








}