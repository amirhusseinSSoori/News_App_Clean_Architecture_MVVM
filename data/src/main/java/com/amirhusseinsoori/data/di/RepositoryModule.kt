package com.amirhusseinsoori.data.di

import com.amirhusseinsoori.data.repository.ArticleNewsRepositoryImpl
import com.amirhusseinsoori.domain.repository.BreakingNewsRepository
import com.amirhusseinsoori.domain.repository.SearchNewsRepository
import com.amirhusseinsoori.data.repository.BreakingNewsRepositoryImpl
import com.amirhusseinsoori.data.repository.SavedNewsRepositoryImpl
import com.amirhusseinsoori.data.repository.SearchNewsRepositoryImpI
import com.amirhusseinsoori.domain.repository.ArticleNewsRepository
import com.amirhusseinsoori.domain.repository.SavedNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    abstract fun bindBreakingNewsRepository(breakingNewsRepositoryImpl: BreakingNewsRepositoryImpl): BreakingNewsRepository


    @Binds
    abstract fun bindSearchNewsRepository(searchNewsRepositoryImpI: SearchNewsRepositoryImpI): SearchNewsRepository

    @Binds
    abstract fun bindArticleNewsRepository(articleNewsRepositoryImpl: ArticleNewsRepositoryImpl): ArticleNewsRepository


    @Binds
    abstract fun bindSavedNewsRepository(searchNewsRepositoryImpl: SavedNewsRepositoryImpl): SavedNewsRepository
}