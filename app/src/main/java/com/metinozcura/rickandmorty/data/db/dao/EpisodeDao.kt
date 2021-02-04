package com.metinozcura.rickandmorty.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.metinozcura.rickandmorty.data.model.Episode

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(episodes: List<Episode>)

    @Query("SELECT * FROM episodes")
    fun pagingSource(): PagingSource<Int, Episode>

    @Query("DELETE FROM episodes")
    suspend fun clearAll()
}