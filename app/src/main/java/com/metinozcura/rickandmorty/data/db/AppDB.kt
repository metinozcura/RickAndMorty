package com.metinozcura.rickandmorty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.metinozcura.rickandmorty.data.db.converter.StringListConverter
import com.metinozcura.rickandmorty.data.db.dao.EpisodeDao
import com.metinozcura.rickandmorty.data.model.Episode

@Database(entities = [Episode::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class AppDB : RoomDatabase() {
    abstract fun episodeDao(): EpisodeDao
}