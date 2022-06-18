package com.amirhusseinsoori.newsapp.presentation.ui.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.domain.entity.Article
import com.amirhusseinsoori.domain.usecase.ArticleUseCase
import com.amirhusseinsoori.newsapp.presentation.util.getArgByGson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: ArticleUseCase
) : ViewModel() {


    private val mutableStateArticle = MutableStateFlow<Article>(Article.empty)
    val state = mutableStateArticle.asStateFlow()

    init {
        getArgByGson<Article>(savedStateHandle.get<String>("article") ?: "").let {
            mutableStateArticle.value = it
        }
    }

    fun insertArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.execute(article)
        }
    }


}