package com.amirhusseinsoori.newsapp.data.di

import com.amirhusseinsoori.domain.repository.BreakingNewsRepository
import com.amirhusseinsoori.domain.repository.SearchNewsRepository
import com.amirhusseinsoori.newsapp.data.repository.BreakingNewsRepositoryImpl
import com.amirhusseinsoori.newsapp.data.repository.SearchNewsRepositoryImpI
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {


    @Binds
    abstract fun bindBreakingNewsRepository(breakingNewsRepositoryImpl: BreakingNewsRepositoryImpl): BreakingNewsRepository


    @Binds
    abstract fun bindSearchNewsRepository(searchNewsRepositoryImpI: SearchNewsRepositoryImpI): SearchNewsRepository
}