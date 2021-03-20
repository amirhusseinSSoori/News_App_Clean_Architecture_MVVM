package com.amirhusseinsoori.newsapp.api.response

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)