package com.velosobr.kaizengaming.domain.usecases

import com.velosobr.kaizengaming.domain.model.Sport

interface GetSportsUseCase {
    suspend operator fun invoke(): List<Sport>
}