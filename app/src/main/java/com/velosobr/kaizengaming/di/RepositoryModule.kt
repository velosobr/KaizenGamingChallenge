package com.velosobr.kaizengaming.di

import com.velosobr.kaizengaming.data.repository.SportsRepository
import com.velosobr.kaizengaming.data.repository.SportsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSportsRepository(sportsRepositoryImpl: SportsRepositoryImpl): SportsRepository {
        return sportsRepositoryImpl
    }
}