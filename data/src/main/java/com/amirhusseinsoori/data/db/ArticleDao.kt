package com.amirhusseinsoori.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.amirhusseinsoori.data.db.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: ArticleEntity): Long

    @Query("SELECT * FROM articlesEntity")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Delete
    suspend fun deleteArticle(article: ArticleEntity)
}