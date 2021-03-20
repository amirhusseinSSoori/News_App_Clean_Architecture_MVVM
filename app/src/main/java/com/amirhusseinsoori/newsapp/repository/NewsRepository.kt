package com.amirhusseinsoori.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.amirhusseinsoori.newsapp.api.response.Article
import com.amirhusseinsoori.newsapp.db.ArticleDao
import com.amirhusseinsoori.newsapp.source.Remote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val remote: Remote) {



    fun getBreakingNews(countryCode: String): LiveData<PagingData<Article>> {
      return remote.getArticles(countryCode)
    }
}