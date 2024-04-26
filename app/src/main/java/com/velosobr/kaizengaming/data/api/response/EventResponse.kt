package com.velosobr.kaizengaming.data.api.response

import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("i") val id: String,
    @SerializedName("si") val sportId: String,
    @SerializedName("d") val name: String,
    @SerializedName("tt") val startTime: Int
)
