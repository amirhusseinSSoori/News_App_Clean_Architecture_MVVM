package com.amirhusseinsoori.newsapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.domain.entity.Article
import com.amirhusseinsoori.domain.usecase.ArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(val useCase: ArticleUseCase) : ViewModel() {


    fun insertArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.execute(article)
        }

    }


}