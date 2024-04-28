package com.velosobr.kaizengaming.presentation

import com.velosobr.kaizengaming.domain.model.Sport

sealed class MainActivityState {
    object Loading : MainActivityState()
    data class Success(val sports: MutableList<Sport>) : MainActivityState()
    class Error(val message: String = "Uh-oh! Something went wrong while fetching the events. \uD83D\uDE13") : MainActivityState()
}