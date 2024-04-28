package com.velosobr.kaizengaming.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object TimeUtils {
    fun convertUnixToTime(unixSeconds: Long): String {
        val date = Date(unixSeconds * 1000L) // convert seconds to milliseconds
        val format = SimpleDateFormat("HH:mm:ss", Locale.US)
        format.timeZone = TimeZone.getTimeZone("UTC") // set timezone to UTC
        return format.format(date)
    }
}