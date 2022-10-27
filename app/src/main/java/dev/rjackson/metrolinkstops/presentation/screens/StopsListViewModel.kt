package dev.rjackson.metrolinkstops.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.rjackson.metrolinkstops.network.metrolinkstops.MetrolinkStopsApi
import dev.rjackson.metrolinkstops.network.metrolinkstops.MetrolinkStopsEntry
import kotlinx.coroutines.launch

private const val TAG = "StopsListViewModel"

enum class StopsListApiStatus { LOADING, ERROR, DONE }

class StopsListViewModel : ViewModel() {
    private val _stops = mutableStateListOf<MetrolinkStopsEntry>()
    val stops: List<MetrolinkStopsEntry>
        get() = _stops

    private val _status = mutableStateOf(StopsListApiStatus.LOADING)
    val status: StopsListApiStatus
        get() = _status.value

    fun getMetrolinks() {
        viewModelScope.launch {
            _status.value = StopsListApiStatus.LOADING
            try {
                _stops.clear()
                _stops.addAll(
                    MetrolinkStopsApi.retrofitService.getStops()
                        .sortedBy { it.stationLocation })
                _status.value = StopsListApiStatus.DONE
            } catch (e: Exception) {
                _stops.clear()
                _status.value = StopsListApiStatus.ERROR
                Log.w(TAG, "Could not load stops: ${e.message}")
            }
        }
    }
}