package com.amirhusseinsoori.newsapp.di

import android.content.Context
import androidx.room.Room
import com.amirhusseinsoori.newsapp.db.ArticleDao
import com.amirhusseinsoori.newsapp.db.MyDataBase
import com.amirhusseinsoori.newsapp.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MyDataBase {
        return Room.databaseBuilder(context,MyDataBase::class.java,DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(myDataBase: MyDataBase): ArticleDao{
        return myDataBase.getArticleDao()
    }
}