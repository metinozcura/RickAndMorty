package com.metinozcura.rickandmorty.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.metinozcura.rickandmorty.data.db.converter.StringListConverter
import com.metinozcura.rickandmorty.data.db.dao.EpisodeDao
import com.metinozcura.rickandmorty.data.db.dao.PageKeyDao
import com.metinozcura.rickandmorty.data.model.Episode
import com.metinozcura.rickandmorty.data.model.PageKey

@Database(entities = [Episode::class, PageKey::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class AppDB : RoomDatabase() {
    abstract fun episodeDao(): EpisodeDao
    abstract fun pageKeyDao(): PageKeyDao

    companion object {
        @Volatile private var instance: AppDB? = null

        fun getDatabase(context: Context): AppDB =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDB::class.java, "rickandmorty")
                .fallbackToDestructiveMigration()
                .build()
    }
}