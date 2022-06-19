package com.amirhusseinsoori.domain.entity

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
    )