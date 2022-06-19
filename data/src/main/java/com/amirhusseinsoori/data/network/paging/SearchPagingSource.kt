package com.amirhusseinsoori.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amirhusseinsoori.common.Constants
import com.amirhusseinsoori.common.Constants.Companion.PAGING_START_PAGE
import com.amirhusseinsoori.domain.entity.Article
import com.amirhusseinsoori.domain.entity.NewsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*



import java.io.IOException

class SearchPagingSource(
    private val httpClient: HttpClient,
    private val query: String
): PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: PAGING_START_PAGE
        return try {

            val response = httpClient.get {
                parameter("q", query)
                parameter("page", position)
                parameter("pageSize", params.loadSize)
                url("${Constants.BASE_URL}${"/v2/everything"}")
            }.body<NewsResponse>()

            val article = response.articles

            LoadResult.Page(
                data = article,
                prevKey = if (position == PAGING_START_PAGE) null else position - 1,
                nextKey = if (article.isEmpty()) null else position + 1
            )
        } catch (exception: Throwable) {
            LoadResult.Error(exception)
        }
    }
}