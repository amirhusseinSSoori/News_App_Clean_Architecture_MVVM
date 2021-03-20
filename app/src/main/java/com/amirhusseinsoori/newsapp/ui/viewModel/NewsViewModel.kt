package com.amirhusseinsoori.newsapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.amirhusseinsoori.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor( val repository: NewsRepository): ViewModel()  {
}