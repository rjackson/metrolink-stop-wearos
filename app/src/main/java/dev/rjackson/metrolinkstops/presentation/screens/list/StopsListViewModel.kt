package dev.rjackson.metrolinkstops.presentation.screens.list

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import dev.rjackson.metrolinkstops.MyApplication
import dev.rjackson.metrolinkstops.data.Stop
import dev.rjackson.metrolinkstops.data.StopsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class StopsListUiState(
    val allStops: List<Stop>
)

class StopsListViewModel(
    private val stopsRepo: StopsRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(StopsListUiState(allStops = emptyList()))
    val uiState: StateFlow<StopsListUiState> = _uiState.asStateFlow()

    fun onFavouriteChange(stop: Stop, checked: Boolean) {
        viewModelScope.launch {
            if (checked) {
                stopsRepo.favouriteStop(stop)
            } else {
                stopsRepo.unFavouriteStop(stop)
            }
        }
    }

    init {
        viewModelScope.launch {
            stopsRepo.getStops().collect() { allStops ->
                _uiState.value = StopsListUiState(
                    allStops = allStops
                )
            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                // val savedStateHandle = extras.createSavedStateHandle()

                return StopsListViewModel(
                    (application as MyApplication).stopsRepo
                ) as T
            }
        }
    }
}