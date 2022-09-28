package com.amirhusseinsoori.newsapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.amirhusseinsoori.domain.usecase.BreakingNewUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val useCase: BreakingNewUseCase) : ViewModel() {


    init {
        callEventListNews() }

    private val _newsState = MutableStateFlow<PagingData<ArticleDomain>>(PagingData.empty())
    val newsState: StateFlow<PagingData<ArticleDomain>> = _newsState



    suspend fun getListNews(): Flow<PagingData<ArticleDomain>> {
        return useCase.execute("us").cachedIn(viewModelScope)
    }


     fun callEventListNews() {
        viewModelScope.launch(Dispatchers.IO) {
            getListNews().collect() { list ->
                _newsState.value = list
            }
        }
    }


}