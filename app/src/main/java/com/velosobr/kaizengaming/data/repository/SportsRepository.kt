package com.velosobr.kaizengaming.data.repository

import com.velosobr.kaizengaming.domain.model.Sport

interface SportsRepository {
    suspend fun getSports(): List<Sport>
}