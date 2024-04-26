package com.velosobr.kaizengaming.data.repository

import com.velosobr.kaizengaming.domain.model.Sport
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

interface SportsRepository {
    suspend fun getSports(): List<Sport>

}