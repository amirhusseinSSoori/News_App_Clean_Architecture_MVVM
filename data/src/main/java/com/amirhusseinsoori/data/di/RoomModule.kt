package com.amirhusseinsoori.data.di

import android.content.Context
import androidx.room.Room
import com.amirhusseinsoori.common.Constants.Companion.DATABASE_NAME

import com.amirhusseinsoori.data.db.ArticleDao
import com.amirhusseinsoori.data.db.MyDataBase

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
        return Room.databaseBuilder(context, MyDataBase::class.java,DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(myDataBase: MyDataBase): ArticleDao {
        return myDataBase.getArticleDao()
    }
}