package dev.rjackson.metrolinkstops.presentation.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.*
import androidx.wear.compose.material.*
import com.google.android.horologist.compose.navscaffold.scrollableColumn
import dev.rjackson.metrolinkstops.network.tfgm.Carriages
import dev.rjackson.metrolinkstops.presentation.StopDetailsApiStatus
import dev.rjackson.metrolinkstops.presentation.StopDetailsViewModel
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun StopDetails(
    modifier: Modifier = Modifier,
    stationLocation: String,
    viewModel: StopDetailsViewModel = viewModel(),
    scrollableState: ScrollState,
    focusRequester: FocusRequester,
    onSetTimeText: (@Composable (Modifier) -> Unit) -> Unit
) {
    val titleStyle = MaterialTheme.typography.title3
    val titleColor = MaterialTheme.colors.primary
    LaunchedEffect(Unit) {
        viewModel.refreshDepartures(stationLocation)
        onSetTimeText @Composable {
            TimeText(
                startCurvedContent = {
                    curvedText(
                        style = CurvedTextStyle(titleStyle),
                        color = titleColor,
                        text = stationLocation
                    )
                },
                startLinearContent = {
                    Text(
                        style = titleStyle,
                        color = titleColor,
                        text = stationLocation
                    )
                }
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = modifier
                .weight(1f)
                .verticalScroll(state = scrollableState)
                .scrollableColumn(
                    focusRequester = focusRequester,
                    scrollableState = scrollableState
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (viewModel.status) {
                StopDetailsApiStatus.DONE -> {
                    for (departure in viewModel.departures) {
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
                    Text(text = "Loading")
                }
                StopDetailsApiStatus.ERROR -> {
                    Text(text = "Error")
                }
            }
        }
        CompactChip(
            onClick = { viewModel.refreshDepartures(stationLocation) },
            label = { Text(text = "Refresh") },
            colors = ChipDefaults.secondaryChipColors()
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
        stationLocation = "Deansgate - Castlefield",
        viewModel = StopDetailsViewModel(),
        scrollableState = ScrollState(0),
        focusRequester = FocusRequester(),
        onSetTimeText = {}
    )
}