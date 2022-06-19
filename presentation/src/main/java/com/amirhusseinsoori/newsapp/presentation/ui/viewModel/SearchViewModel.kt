package com.amirhusseinsoori.newsapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.amirhusseinsoori.domain.model.ApiError
import com.amirhusseinsoori.domain.usecase.SearchNewUseCase
import com.amirhusseinsoori.domain.usecase.base.UseCaseResponse
import com.amirhusseinsoori.domain.entity.ArticleDomain

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository: SearchNewUseCase) : ViewModel() {


    var getDetails = MutableStateFlow<DetailsNetwork>(DetailsNetwork.Empty)
    val getDetailsCollect: StateFlow<DetailsNetwork> = getDetails


    fun searchNews(query: String) {
        return repository.invoke(viewModelScope, query, object :
            UseCaseResponse<Flow<PagingData<ArticleDomain>>> {
            override fun onSuccess(result: Flow<PagingData<ArticleDomain>>) {
                getDetails.value = DetailsNetwork.Success(result)
            }
            override fun onError(apiError: ApiError?) {
                getDetails.value = DetailsNetwork.Failed(apiError!!.message)
            }

        })
    }

    sealed class DetailsNetwork() {
        object Empty : DetailsNetwork()
        class Success(var data: Flow<PagingData<ArticleDomain>>) : DetailsNetwork()
        class Failed(var message: String?) : DetailsNetwork()
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}

