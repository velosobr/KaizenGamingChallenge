package com.velosobr.kaizengaming.data.repository

import com.velosobr.kaizengaming.data.api.SportsApiService
import com.velosobr.kaizengaming.data.api.response.SportResponse
import com.velosobr.kaizengaming.data.mapper.SportMapper
import com.velosobr.kaizengaming.domain.model.Sport
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import retrofit2.Response

class SportsRepositoryImplTest {

    private val apiService = mockk<SportsApiService>()
    private val sportMapper = mockk<SportMapper>()
    private val repository = SportsRepositoryImpl(apiService, sportMapper)

    @Test
    fun `getSports returns list of sports when response is successful`() = runBlocking {
        val sportResponse = SportResponse("1", "Soccer", emptyList())
        val expectedSport = Sport("1", "Soccer", emptyList())
        coEvery { apiService.getSports() } returns Response.success(listOf(sportResponse))
        coEvery { sportMapper.mapFromSportResponse(sportResponse) } returns expectedSport

        val result = repository.getSports()

        assertEquals(listOf(expectedSport), result)
    }

    @Test
    fun `getSports throws exception when response is unsuccessful`() = runBlocking {
        val mockResponseBody = mockk<ResponseBody>()
        every { mockResponseBody.contentType() } returns null
        every { mockResponseBody.contentLength() } returns 0L
        coEvery { apiService.getSports() } returns Response.error(400, mockResponseBody)

        val exception = assertThrows(Exception::class.java) {
            runBlocking { repository.getSports() }
        }

        assertEquals("Response was not successful. Code: 400, Message: Response.error()", exception.message)

    }
}