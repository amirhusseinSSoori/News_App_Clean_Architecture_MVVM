package com.amirhusseinsoori.domain.entity

data class ArticleDomain(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: SourceDomain? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val id: Long? = null
) {
    companion object {
        val empty = ArticleDomain(
            author = "",
            content = "",
            description = "",
            publishedAt = "",
            source = SourceDomain(id = "", name = ""),
            title = "",
            url = "",
            urlToImage = "",
            id = 0
        )
    }
}