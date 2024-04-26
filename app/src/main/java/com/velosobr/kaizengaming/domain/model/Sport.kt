package com.velosobr.kaizengaming.domain.model

/**
 * Sport domain model
 */
data class Sport(
    /**
     * Sport id
     */
    val i: String,

    /**
     * Sport name
     */
    val d: String,

    /**
     * Active events
     */
    val e: List<Event>
)