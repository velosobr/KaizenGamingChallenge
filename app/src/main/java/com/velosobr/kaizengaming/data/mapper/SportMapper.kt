package com.velosobr.kaizengaming.data.mapper

import com.velosobr.kaizengaming.data.api.response.EventResponse
import com.velosobr.kaizengaming.data.api.response.SportResponse
import com.velosobr.kaizengaming.domain.model.Event
import com.velosobr.kaizengaming.domain.model.Sport
import javax.inject.Inject

class SportMapper @Inject constructor() {
    fun mapFromSportResponse(sportResponse: SportResponse): Sport {
        return Sport(
            i = sportResponse.id,
            d = sportResponse.name,
            e = sportResponse.events.map { mapFromEvent(it) }
        )
    }

    private fun mapFromEvent(event: EventResponse): Event {
        return Event(
            i = event.id,
            si = event.sportId,
            d = event.name,
            tt = event.startTime
        )
    }
}