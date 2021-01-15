package com.metinozcura.rickandmorty.di

import com.metinozcura.rickandmorty.data.repository.character.CharacterRepository
import com.metinozcura.rickandmorty.data.repository.character.CharacterRepositoryImpl
import com.metinozcura.rickandmorty.data.repository.episode.EpisodeRepository
import com.metinozcura.rickandmorty.data.repository.episode.EpisodeRepositoryImpl
import com.metinozcura.rickandmorty.data.repository.location.LocationRepository
import com.metinozcura.rickandmorty.data.repository.location.LocationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository

    @Binds
    abstract fun bindEpisodeRepository(
        episodeRepositoryImpl: EpisodeRepositoryImpl
    ): EpisodeRepository

    @Binds
    abstract fun bindLocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository
}