package com.velosobr.kaizengaming.data.mapper

import com.velosobr.kaizengaming.data.api.response.EventResponse
import com.velosobr.kaizengaming.data.api.response.SportResponse
import com.velosobr.kaizengaming.domain.model.Event
import com.velosobr.kaizengaming.domain.model.Sport
import org.junit.Assert.assertEquals
import org.junit.Test

class SportMapperTest {

    private val sportMapper = SportMapper()

    @Test
    fun `mapFromSportResponse returns correct Sport when given SportResponse`() {
        // Arrange
        val eventResponse = EventResponse("1", "1", "Event", 1714345560)
        val sportResponse = SportResponse("1", "Sport", listOf(eventResponse))
        val expectedEvent = Event("1", "1", "Event", 1714345560)
        val expectedSport = Sport("1", "Sport", listOf(expectedEvent))

        // Act
        val result = sportMapper.mapFromSportResponse(sportResponse)

        // Assert
        assertEquals(expectedSport, result)
    }
}