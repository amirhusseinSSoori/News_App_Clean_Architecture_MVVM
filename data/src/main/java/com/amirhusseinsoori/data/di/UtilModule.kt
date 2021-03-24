package com.amirhusseinsoori.newsapp.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*


@Module
@InstallIn(SingletonComponent::class)
object UtilModule {


    @Provides
    fun setUpTimer(): Timer = Timer()

}