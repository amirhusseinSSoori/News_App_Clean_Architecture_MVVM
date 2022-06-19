package com.amirhusseinsoori.domain.entity


data class Article(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val id: Long? = null
) {
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