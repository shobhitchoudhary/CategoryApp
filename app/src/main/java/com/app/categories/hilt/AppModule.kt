package com.app.categories.hilt

import com.app.categories.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl(): String = "https://www.jsonkeeper.com/b/"

    @Provides
    fun provideRetrofitBuilder(baseUrl: String): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}