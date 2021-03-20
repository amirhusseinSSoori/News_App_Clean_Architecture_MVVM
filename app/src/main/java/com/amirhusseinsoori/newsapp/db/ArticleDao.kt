package com.amirhusseinsoori.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.amirhusseinsoori.newsapp.db.entity.ArticleEntity

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: ArticleEntity): Long

    @Query("SELECT * FROM articlesEntity")
    fun getAllArticles(): LiveData<List<ArticleEntity>>

    @Delete
    suspend fun deleteArticle(article: ArticleEntity)
}