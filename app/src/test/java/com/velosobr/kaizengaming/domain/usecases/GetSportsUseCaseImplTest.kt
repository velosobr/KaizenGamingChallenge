package com.velosobr.kaizengaming.domain.usecases

import com.velosobr.kaizengaming.data.repository.SportsRepository
import com.velosobr.kaizengaming.domain.model.Sport
import io.mockk.coEvery
import org.junit.Assert.*
import io.mockk.mockk
import kotlinx.coroutines.runBlocking

import org.junit.Test

class GetSportsUseCaseImplTest {


    private val mockSportsRepository = mockk<SportsRepository>()
    private val getSportsUseCase = GetSportsUseCaseImpl(mockSportsRepository)

    @Test
    fun `invoke returns list of sports when repository response is successful`() = runBlocking {
        val expectedSport = Sport("1", "Soccer", emptyList())
        coEvery { mockSportsRepository.getSports() } returns listOf(expectedSport)

        val result = getSportsUseCase.invoke()

        assertEquals(listOf(expectedSport), result)
    }

    @Test
    fun `invoke throws exception when repository response is unsuccessful`() = runBlocking {
        val exception = Exception("Error fetching sports")
        coEvery { mockSportsRepository.getSports() } throws exception

        try {
            getSportsUseCase.invoke()
            fail("Expected an Exception to be thrown")
        } catch (e: Exception) {
            assertEquals(exception, e)
        }
    }
}