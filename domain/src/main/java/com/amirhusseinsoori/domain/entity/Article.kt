package com.amirhusseinsoori.domain.entity

import java.io.Serializable

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,
    val id: Long? = null
) : Serializable {
    companion object {
        val empty = Article(
            author = "",
            content = "",
            description = "",
            publishedAt = "",
            source = Source(id = "", name = ""),
            title = "",
            url = "",
            urlToImage = "",
            id = 0
        )
    }
}