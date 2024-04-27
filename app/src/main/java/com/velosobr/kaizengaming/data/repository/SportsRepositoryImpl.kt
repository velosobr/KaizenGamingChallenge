package com.velosobr.kaizengaming.data.repository

import com.velosobr.kaizengaming.data.api.SportsApiService
import com.velosobr.kaizengaming.data.mapper.SportMapper
import com.velosobr.kaizengaming.domain.model.Sport
import javax.inject.Inject


class SportsRepositoryImpl @Inject constructor(
    private val sportsApiService: SportsApiService,
    private val sportMapper: SportMapper
) : SportsRepository {
    override suspend fun getSports(): Result<List<Sport>> {
        return try {
            val response = sportsApiService.getSports()
            if (response.isSuccessful) {
                val sportResponses = response.body()
                if (sportResponses != null) {
                    Result.success(sportResponses.map { sportMapper.mapFromSportResponse(it) })
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Response was not successful. Code: ${response.code()}, Message: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Failed to fetch sports: ${e.message}", e))
        }
    }
}