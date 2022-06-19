package com.amirhusseinsoori.domain.entity

data class NewsDomain(
    val articles: List<ArticleDomain>,
    val status: String,
    val totalResults: Int
)