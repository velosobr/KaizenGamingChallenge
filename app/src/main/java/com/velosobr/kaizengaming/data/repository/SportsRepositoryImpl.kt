package com.velosobr.kaizengaming.data.repository

import com.velosobr.kaizengaming.data.api.SportsApiService
import com.velosobr.kaizengaming.data.mapper.SportMapper
import com.velosobr.kaizengaming.domain.model.Sport
import javax.inject.Inject

class SportsRepositoryImpl @Inject constructor(
    private val sportsApiService: SportsApiService,
    private val sportMapper: SportMapper
) : SportsRepository {
    override suspend fun getSports(): List<Sport> {
        val response = sportsApiService.getSports()
        if (response.isSuccessful) {
            val sportResponses = response.body()
            if (sportResponses != null) {
                return sportResponses.map { sportMapper.mapFromSportResponse(it) }
            } else {
                throw Exception("Response body is null")
            }
        } else {
            throw Exception("Response was not successful. Code: ${response.code()}, Message: ${response.message()}")
        }
    }
}