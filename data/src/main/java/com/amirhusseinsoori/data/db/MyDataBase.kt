package com.amirhusseinsoori.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.amirhusseinsoori.data.db.converter.Converters
import com.amirhusseinsoori.data.db.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MyDataBase: RoomDatabase() {


    abstract fun getArticleDao(): ArticleDao
}