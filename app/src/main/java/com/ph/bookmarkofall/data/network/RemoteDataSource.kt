package com.ph.bookmarkofall.data.network

import com.ph.bookmarkofall.data.common.BASE_WEB_URL
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource{
    private fun providesHostingWebUrl() = BASE_WEB_URL

    private fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        return client.build()
    }


    fun <Api> buildApi(
        api: Class<Api>): Api {
        return Retrofit.Builder()
            .baseUrl(providesHostingWebUrl())
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}

