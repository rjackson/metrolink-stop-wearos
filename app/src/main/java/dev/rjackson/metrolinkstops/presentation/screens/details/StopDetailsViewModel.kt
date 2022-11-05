package dev.rjackson.metrolinkstops.presentation.screens.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import dev.rjackson.metrolinkstops.network.metrolinkstops.MetrolinkStopDetail
import dev.rjackson.metrolinkstops.network.metrolinkstops.MetrolinkStopsApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "StopDetailsViewModel"

sealed interface StopDetailsUiState {
    data class Success(
        val stopDetail: MetrolinkStopDetail
    ) : StopDetailsUiState

    object Error : StopDetailsUiState
    object Loading : StopDetailsUiState
}

class StopDetailsViewModel(
    private val stopName: String
) : ViewModel() {
    private val _uiState: MutableStateFlow<StopDetailsUiState> =
        MutableStateFlow(StopDetailsUiState.Loading)
    val uiState: StateFlow<StopDetailsUiState> = _uiState.asStateFlow()

    fun refreshDepartures() {
        viewModelScope.launch {
            _uiState.value = StopDetailsUiState.Loading
            try {
                val stopDetail = MetrolinkStopsApi.retrofitService.getStop(name = stopName)
                _uiState.value = StopDetailsUiState.Success(stopDetail)
            } catch (e: Exception) {
                _uiState.value = StopDetailsUiState.Error
                Log.w(TAG, "Could not load stops: ${e.message}")
            }
        }
    }

    init {
        refreshDepartures()
    }

    class Factory(private val stopName: String) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            return StopDetailsViewModel(
                stopName
            ) as T
        }
    }
}