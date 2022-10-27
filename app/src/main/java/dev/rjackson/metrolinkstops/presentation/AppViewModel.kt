package dev.rjackson.metrolinkstops.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.rjackson.metrolinkstops.network.metrolinkstops.MetrolinkStopsApi
import dev.rjackson.metrolinkstops.network.metrolinkstops.MetrolinkStopsEntry
import kotlinx.coroutines.launch

private const val TAG = "MetrolinksViewModel"

enum class AppApiStatus { LOADING, ERROR, DONE }

class AppViewModel : ViewModel() {
    private val _stops = mutableStateListOf<MetrolinkStopsEntry>()
    val stops: List<MetrolinkStopsEntry>
        get() = _stops

    private val _status = mutableStateOf(AppApiStatus.LOADING)
    val status: AppApiStatus
        get() = _status.value

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: String?
        get() = _errorMessage.value

    fun getMetrolinks() {
        viewModelScope.launch {
            _status.value = AppApiStatus.LOADING
            _errorMessage.value = null
            try {
                _stops.clear()
                _stops.addAll(
                    MetrolinkStopsApi.retrofitService.getStops()
                        .sortedBy { it.stationLocation })
                _status.value = AppApiStatus.DONE
            } catch (e: Exception) {
                _stops.clear()
                _status.value = AppApiStatus.ERROR
                _errorMessage.value = e.message
                Log.w(TAG, "Could not load stops: ${e.message}")
            }
        }
    }
}