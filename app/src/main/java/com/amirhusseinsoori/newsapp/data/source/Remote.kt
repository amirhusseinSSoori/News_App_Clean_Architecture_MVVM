package com.amirhusseinsoori.newsapp.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.amirhusseinsoori.newsapp.data.network.NewsAPI
import com.amirhusseinsoori.newsapp.data.network.paging.BreakingPagingSource
import com.amirhusseinsoori.newsapp.data.network.paging.SearchPagingSource
import javax.inject.Inject

class Remote @Inject constructor(val api: NewsAPI) {

    fun getArticlesNews(countryCode: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { BreakingPagingSource(api, countryCode) }
        ).flow


    fun searchArticles(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(api, query) }
        ).flow
}