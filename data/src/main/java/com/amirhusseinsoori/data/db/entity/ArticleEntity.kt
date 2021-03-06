package com.amirhusseinsoori.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amirhusseinsoori.domain.entity.Source

@Entity(
    tableName = "articlesEntity"
)
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)