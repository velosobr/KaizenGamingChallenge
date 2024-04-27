package com.velosobr.kaizengaming.di

import com.velosobr.kaizengaming.domain.usecases.GetSportsUseCase
import com.velosobr.kaizengaming.domain.usecases.GetSportsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetSportsUseCase(getSportsUseCaseImpl: GetSportsUseCaseImpl): GetSportsUseCase {
        return getSportsUseCaseImpl
    }
}