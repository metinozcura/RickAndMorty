package com.metinozcura.rickandmorty.di

import android.content.Context
import com.metinozcura.rickandmorty.data.db.AppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDB.getDatabase(appContext)
}