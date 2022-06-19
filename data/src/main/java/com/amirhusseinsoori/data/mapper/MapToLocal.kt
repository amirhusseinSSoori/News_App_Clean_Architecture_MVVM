package com.amirhusseinsoori.data.mapper

import com.amirhusseinsoori.data.db.entity.ArticleEntity
import com.amirhusseinsoori.data.db.entity.SourceEntity
import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.amirhusseinsoori.domain.entity.SourceDomain


fun SourceDomain.mapLocalToSourceDomain(): SourceEntity {
    return SourceEntity(id = id?:"", name = name?:"")
}

fun ArticleDomain.mapLocalToArticleDomain(): ArticleEntity {
    return ArticleEntity(
        author = author?:"",
        content = content?:"",
        description = description?:"",
        publishedAt = publishedAt?:"",
        source = source!!.mapLocalToSourceDomain(),
        title = title?:"",
        url = url?:"",
        urlToImage = urlToImage?:"",
        id = id
    )
}
fun SourceEntity.mapLocalToSourceEntity(): SourceDomain {
    return SourceDomain(id = id?:"", name = name?:"")
}

fun ArticleEntity.mapLocalToArticleEntity(): ArticleDomain {
    return ArticleDomain(
        author = author?:"",
        content = content?:"",
        description = description?:"",
        publishedAt = publishedAt?:"",
        source = source.mapLocalToSourceEntity(),
        title = title?:"",
        url = url?:"",
        urlToImage = urlToImage?:"",
        id = id
    )
}

fun List<ArticleEntity>.mapLocalToListArticleEntity(): List<ArticleDomain> {
    return map {
        it.mapLocalToArticleEntity()
    }
}


