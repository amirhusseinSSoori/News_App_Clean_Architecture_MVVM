package com.amirhusseinsoori.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.amirhusseinsoori.data.network.paging.BreakingPagingSource
import com.amirhusseinsoori.data.network.paging.SearchPagingSource
import io.ktor.client.*
import javax.inject.Inject

class Remote @Inject constructor(private val httpClient: HttpClient) {

    fun getArticlesNews(countryCode: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { BreakingPagingSource(httpClient, countryCode) }
        ).flow


    fun searchArticles(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(httpClient, query) }
        ).flow
}