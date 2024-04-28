package com.velosobr.kaizengaming.domain.model

data class Event(
    val i: String, // event id
    val si: String, // sport id
    val d: String, // event name
    val tt: Int, // event start time
    var isFavorite: Boolean = false

) {
    val competitors: List<String>
        get() = d.split("-")
}