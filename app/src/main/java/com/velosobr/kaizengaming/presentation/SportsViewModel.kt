package com.velosobr.kaizengaming.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.kaizengaming.domain.model.Sport
import com.velosobr.kaizengaming.domain.usecases.GetSportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(private val getSportsUseCase: GetSportsUseCase) : ViewModel() {

    private val _sports = MutableStateFlow<List<Sport>>(emptyList())
    val sports: StateFlow<List<Sport>> = _sports

    init {
        fetchSports()
    }

    private fun fetchSports() {
        viewModelScope.launch {
            val sports = getSportsUseCase()
            _sports.value = sports
        }
    }
}