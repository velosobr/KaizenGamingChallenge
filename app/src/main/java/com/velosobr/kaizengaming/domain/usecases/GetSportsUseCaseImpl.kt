package com.velosobr.kaizengaming.domain.usecases

import com.velosobr.kaizengaming.data.repository.SportsRepository
import com.velosobr.kaizengaming.domain.model.Sport
import javax.inject.Inject

class GetSportsUseCaseImpl @Inject constructor(
    private val sportsRepository: SportsRepository
) : GetSportsUseCase {
    override suspend fun invoke(): List<Sport> {
        return sportsRepository.getSports()
    }
}