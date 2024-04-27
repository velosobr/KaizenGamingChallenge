package com.velosobr.kaizengaming.data.api

import com.velosobr.kaizengaming.data.api.response.SportResponse
import retrofit2.Response
import retrofit2.http.GET

interface SportsApiService {
    @GET("sports")
    suspend fun getSports(): Response<List<SportResponse>>

}