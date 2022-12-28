package com.zibran.learningprojects.wallpaper_app.di

import android.content.Context
import com.zibran.learningprojects.wallpaper_app.activities.HomeActivity
import com.zibran.learningprojects.wallpaper_app.adapter.WallpaperAdapter
import com.zibran.learningprojects.wallpaper_app.db.WallDatabase
import com.zibran.learningprojects.wallpaper_app.db.WallpaperDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideWallDbInstance(@ApplicationContext context: Context): WallDatabase {
        return WallDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideWallDao(wallDatabase: WallDatabase): WallpaperDao {
        return wallDatabase.getWallDao()
    }

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

//    @Singleton
//    @Provides
//    fun provideAdapter(context: HomeActivity): WallpaperAdapter {
//        return WallpaperAdapter(context)
//    }

}