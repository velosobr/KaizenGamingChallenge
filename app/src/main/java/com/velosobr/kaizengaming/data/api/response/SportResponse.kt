package com.velosobr.kaizengaming.data.api.response

import com.google.gson.annotations.SerializedName

data class SportResponse(
    @SerializedName("i") val id: String,
    @SerializedName("d") val name: String,
    @SerializedName("e") val events: List<EventResponse>
)