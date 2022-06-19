package com.amirhusseinsoori.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.amirhusseinsoori.data.network.model.Article
import com.amirhusseinsoori.data.network.model.Source
import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.amirhusseinsoori.domain.entity.SourceDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Source.mapRemoteToSourceDomain(): SourceDomain {
    return SourceDomain(id = id, name = name)
}

fun Article.mapRemoteToArticleDomain(): ArticleDomain {
    return ArticleDomain(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source?.mapRemoteToSourceDomain(),
        title = title,
        url = url,
        urlToImage = urlToImage,
        id = id
    )
}
fun Flow<PagingData<Article>>.mapRemoteToFlowArticleDomain(): Flow<PagingData<ArticleDomain>> {
    return map {paging->
        paging.map {article->
            article.mapRemoteToArticleDomain()
        }
    }
}
