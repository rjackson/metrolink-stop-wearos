package dev.rjackson.metrolinkstops.presentation

import androidx.lifecycle.ViewModel
import dev.rjackson.metrolinkstops.data.Stop
import dev.rjackson.metrolinkstops.data.StopsRepo

class StopsListViewModel(stopsRepo: StopsRepo = StopsRepo()) : ViewModel() {
    val stops: List<Stop> = stopsRepo.getStops()
}