package com.velosobr.kaizengaming.data.repository

import com.velosobr.kaizengaming.data.api.SportsApi
import com.velosobr.kaizengaming.data.mapper.SportMapper
import com.velosobr.kaizengaming.domain.model.Sport
import javax.inject.Inject


class SportsRepositoryImpl @Inject constructor(
    private val sportsApi: SportsApi,
    private val sportMapper: SportMapper
) : SportsRepository {
    override suspend fun getSports(): List<Sport> {
        val response = sportsApi.getSports()
        if (response.isSuccessful) {
            val sportResponses = response.body()
            if (sportResponses != null) {
                return sportResponses.map { sportMapper.mapFromSportResponse(it) }
            }
        }
        throw Exception("Failed to fetch sports")
    }
}