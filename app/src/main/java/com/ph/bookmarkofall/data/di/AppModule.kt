package com.ph.bookmarkofall.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


/*    @Singleton
    @Provides
    fun providePostPublicApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): PostPublicApi {
        return remoteDataSource.buildApi(PostPublicApi::class.java,context)
    }*/

}