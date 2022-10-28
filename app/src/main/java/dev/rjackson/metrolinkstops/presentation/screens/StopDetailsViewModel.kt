package dev.rjackson.metrolinkstops.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.rjackson.metrolinkstops.network.metrolinkstops.MetrolinkStopDetail
import dev.rjackson.metrolinkstops.network.metrolinkstops.MetrolinkStopsApi
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "StopDetailsViewModel"

enum class StopDetailsApiStatus { LOADING, ERROR, DONE }

class StopDetailsViewModel : ViewModel() {
    private val _departures = mutableStateListOf<MetrolinkStopDetail.DepartureEntry>()
    val departures: List<MetrolinkStopDetail.DepartureEntry>
        get() = _departures

    private val _lastUpdated = mutableStateOf<Date?>(null)
    val lastUpdated: Date?
        get() = _lastUpdated.value

    private val _status = mutableStateOf(StopDetailsApiStatus.LOADING)
    val status: StopDetailsApiStatus
        get() = _status.value

    fun refreshDepartures(stationLocation: String) {
        viewModelScope.launch {
            _status.value = StopDetailsApiStatus.LOADING
            try {
                val stop = MetrolinkStopsApi.retrofitService.getStop(name = stationLocation)
                _departures.clear()
                _departures.addAll(stop.departures)
                _lastUpdated.value = stop.lastUpdated
                _status.value = StopDetailsApiStatus.DONE
            } catch (e: Exception) {
                _departures.clear()
                _status.value = StopDetailsApiStatus.ERROR
                Log.w(TAG, "Could not load stops: ${e.message}")
            }
        }
    }
}