package com.amirhusseinsoori.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amirhusseinsoori.common.Constants.Companion.PAGING_START_PAGE
import com.amirhusseinsoori.data.network.NewsAPI

import com.amirhusseinsoori.domain.entity.Article

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