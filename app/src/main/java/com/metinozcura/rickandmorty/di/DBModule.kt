package com.metinozcura.rickandmorty.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
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
    @Provides
    @Singleton
    fun provideRoomDB(
        @ApplicationContext context: Context,
    ): RoomDatabase =
        Room.databaseBuilder(
            context,
            AppDB::class.java, "rickandmorty"
        ).build()
}