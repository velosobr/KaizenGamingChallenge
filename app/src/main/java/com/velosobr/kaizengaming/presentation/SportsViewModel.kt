package com.velosobr.kaizengaming.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.kaizengaming.domain.usecases.GetSportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val getSportsUseCase: GetSportsUseCase
) : ViewModel() {

    private var _state = MutableLiveData<MainActivityState>(MainActivityState.Loading)
    val state: LiveData<MainActivityState>
        get() = _state

    fun fetchSports() = viewModelScope.launch {
        _state.value = MainActivityState.Loading
        runCatching {
            val sports = getSportsUseCase.invoke()
            if (sports.isEmpty()) {
                _state.value = MainActivityState.Error("Oops! Looks like there\\'s no event to display. \uD83D\uDE41")
            } else {
                _state.value = MainActivityState.Success(sports)
            }
        }.onFailure {
            _state.value = MainActivityState.Error()
        }
    }
}