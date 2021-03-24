package com.amirhusseinsoori.newsapp.data.di

import android.content.Context
import androidx.room.Room
import com.amirhusseinsoori.newsapp.data.db.ArticleDao
import com.amirhusseinsoori.newsapp.data.db.MyDataBase
import com.amirhusseinsoori.newsapp.presentation.util.Constants.Companion.DATABASE_NAME
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