package com.amirhusseinsoori.newsapp.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.amirhusseinsoori.newsapp.api.NewsAPI
import com.amirhusseinsoori.newsapp.util.BreakingPagingSource
import javax.inject.Inject

class Remote @Inject constructor(val api: NewsAPI) {

    fun getArticles(countryCode: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { BreakingPagingSource(api, countryCode) }
        ).liveData
}