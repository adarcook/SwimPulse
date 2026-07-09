package com.example.swimpulse

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkoutViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WorkoutUiState())
    val uiState: StateFlow<WorkoutUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    private var startTimeMillis: Long = 0L

    fun startWorkout() {
        startTimeMillis = SystemClock.elapsedRealtime()
        _uiState.value = WorkoutUiState(isSwimming = true)

        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                _uiState.update { currentState ->
                    currentState.copy(
                        elapsedMillis = SystemClock.elapsedRealtime() - startTimeMillis,
                    )
                }
                delay(1_000)
            }
        }
    }

    fun stopWorkout() {
        timerJob?.cancel()
        timerJob = null
        _uiState.update { currentState ->
            currentState.copy(isSwimming = false)
        }
    }

    override fun onCleared() {
        timerJob?.cancel()
        super.onCleared()
    }
}
