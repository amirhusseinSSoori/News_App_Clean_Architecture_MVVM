package com.amirhusseinsoori.newsapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.domain.entity.Article
import com.amirhusseinsoori.domain.usecase.AllNewsUseCase
import com.amirhusseinsoori.domain.usecase.ArticleUseCase
import com.amirhusseinsoori.domain.usecase.DeleteNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(
    private val allNewsUseCase: AllNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase,
    private val articleUseCase: ArticleUseCase
) : ViewModel() {
    fun getAllNews() = allNewsUseCase.execute()

    fun deleteNewsArticle(article: Article) = viewModelScope.launch(Dispatchers.IO)  {
        deleteNewsUseCase.execute(article)
    }

    fun insertArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            articleUseCase.execute(article)
        }

    }
}
