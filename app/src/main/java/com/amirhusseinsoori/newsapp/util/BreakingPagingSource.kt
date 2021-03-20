package com.amirhusseinsoori.newsapp.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amirhusseinsoori.newsapp.api.NewsAPI
import com.amirhusseinsoori.newsapp.api.response.Article
import com.amirhusseinsoori.newsapp.api.response.NewsResponse
import com.amirhusseinsoori.newsapp.util.Constants.Companion.PAGING_START_PAGE
import retrofit2.HttpException
import java.io.IOException

class BreakingPagingSource(
    private val api: NewsAPI,
    private val countryCode: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: PAGING_START_PAGE

        return try {
            val response = api.getBreakingNews(countryCode, position, params.loadSize)
            val article = response.articles
            LoadResult.Page(
                data = article,
                prevKey = if (position == PAGING_START_PAGE) null else position - 1,
                nextKey = if (article.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = state.anchorPosition
}