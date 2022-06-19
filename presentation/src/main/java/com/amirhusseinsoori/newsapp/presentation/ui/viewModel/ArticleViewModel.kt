package com.amirhusseinsoori.newsapp.presentation.ui.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.data.network.model.Article
import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.amirhusseinsoori.domain.usecase.ArticleUseCase
import com.amirhusseinsoori.newsapp.presentation.util.getArgByGson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: ArticleUseCase
) : ViewModel() {


    private val mutableStateArticle = MutableStateFlow<ArticleDomain>(ArticleDomain.empty)
    val state = mutableStateArticle.asStateFlow()

    init {
        getArgByGson<ArticleDomain>(savedStateHandle.get<String>("article") ?: "").let {
            mutableStateArticle.value = it
        }
    }

    fun insertArticle(article: ArticleDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.execute(article)
        }
    }


}