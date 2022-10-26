package dev.rjackson.metrolinkstops.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.rjackson.metrolinkstops.network.Metrolink
import dev.rjackson.metrolinkstops.network.TfgmApi
import kotlinx.coroutines.launch

private const val TAG = "MetrolinksViewModel"

enum class MetrolinksApiStatus { LOADING, ERROR, DONE }

class MetrolinksViewModel : ViewModel() {
    private val _metrolinks = mutableStateListOf<Metrolink>()
    val metrolinks: List<Metrolink>
        get() = _metrolinks

    private val _status = mutableStateOf(MetrolinksApiStatus.LOADING)
    val status: MetrolinksApiStatus
        get() = _status.value

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: String?
        get() = _errorMessage.value

    fun getMetrolinks() {
        viewModelScope.launch {
            _status.value = MetrolinksApiStatus.LOADING
            _errorMessage.value = null
            try {
                _metrolinks.clear()
                _metrolinks.addAll(TfgmApi.retrofitService.getAllMetrolinks())
                _status.value = MetrolinksApiStatus.DONE
            } catch (e: Exception) {
                _metrolinks.clear()
                _status.value = MetrolinksApiStatus.ERROR
                _errorMessage.value = e.message
                Log.w(TAG, "Could not load metrolinks: ${e.message}")
            }
        }
    }
}