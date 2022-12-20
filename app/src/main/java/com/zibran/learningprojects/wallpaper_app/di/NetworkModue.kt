package com.zibran.learningprojects.wallpaper_app.di

import com.zibran.learningprojects.wallpaper_app.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com/api/?key=32178486-df3a375645a9a74666e8fca3e")
            .addConverterFactory(GsonConverterFactory.create()).build()

    }

    @Provides
    fun getInstance(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}