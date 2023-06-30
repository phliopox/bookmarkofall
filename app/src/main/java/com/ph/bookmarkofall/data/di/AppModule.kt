package com.ph.bookmarkofall.data.di

import com.ph.bookmarkofall.data.api.BookMarksApi
import com.ph.bookmarkofall.data.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun bookMarksApi(
    remoteDataSource: RemoteDataSource
    ) : BookMarksApi {
       return remoteDataSource.buildApi(BookMarksApi::class.java)
    }
}