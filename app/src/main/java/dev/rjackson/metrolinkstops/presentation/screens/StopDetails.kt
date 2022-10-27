package dev.rjackson.metrolinkstops.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.*
import dev.rjackson.metrolinkstops.network.tfgm.Carriages
import dev.rjackson.metrolinkstops.presentation.StopDetailsApiStatus
import dev.rjackson.metrolinkstops.presentation.StopDetailsViewModel
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun StopDetails(
    modifier: Modifier = Modifier,
    stationLocation: String,
    viewModel: StopDetailsViewModel = viewModel()
) {
    LaunchedEffect(Unit, block = {
        viewModel.refreshDepartures(stationLocation)
    })

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScalingLazyColumn(
            modifier = modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = stationLocation,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.title3
                )
            }

            when (viewModel.status) {
                StopDetailsApiStatus.DONE -> {
                    items(viewModel.departures) { departure ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = departure.destination)
                            DepartureText(type = departure.type)
                            Text(text = departure.wait.toString())
                        }
                    }
                }
                StopDetailsApiStatus.LOADING -> {
                    item { Text(text = "Loading") }
                }
                StopDetailsApiStatus.ERROR -> {
                    item { Text(text = "Error") }
                }
            }
        }
        CompactChip(
            onClick = { viewModel.refreshDepartures(stationLocation) },
            label = { Text(text = "Refresh") }
        )
    }

}

@Composable
fun DepartureText(type: Carriages) {
    val label = when (type) {
        Carriages.DOUBLE -> "dbl"
        else -> ""
    }
    Text(text = label)
}

@WearDevicePreview
@Composable
fun StopDetailsPreview() {
    StopDetails(
        stationLocation = "Good Station",
        viewModel = StopDetailsViewModel()
    )
}