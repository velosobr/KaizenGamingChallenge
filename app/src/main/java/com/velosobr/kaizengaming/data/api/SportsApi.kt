package com.velosobr.kaizengaming.data.api

import com.velosobr.kaizengaming.data.api.response.SportResponse
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.http.GET

interface SportsApi {
    @GET("sports")
    fun getSports(): Response<List<SportResponse>>

}