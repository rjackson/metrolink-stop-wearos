package dev.rjackson.metrolinkstops.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.*
import androidx.wear.compose.material.*
import dev.rjackson.metrolinkstops.network.metrolinkstops.MetrolinkStopDetail
import dev.rjackson.metrolinkstops.network.tfgm.Carriages
import dev.rjackson.metrolinkstops.network.tfgm.Status
import dev.rjackson.metrolinkstops.presentation.StopDetailsApiStatus
import dev.rjackson.metrolinkstops.presentation.StopDetailsViewModel
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun StopDetails(
    modifier: Modifier = Modifier,
    stationLocation: String,
    viewModel: StopDetailsViewModel = viewModel(),
    scalingLazyListState: ScalingLazyListState,
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

    StopDetailsColumn(
        status = viewModel.status,
        departures = viewModel.departures,
        lastUpdated = viewModel.lastUpdated,
        scalingLazyListState = scalingLazyListState,
        onClickRefresh = {
            viewModel.refreshDepartures(
                stationLocation
            )
        },
        modifier
    )
}

@Composable
fun StopDetailsColumn(
    status: StopDetailsApiStatus,
    departures: List<MetrolinkStopDetail.DepartureEntry>,
    lastUpdated: String?,
    scalingLazyListState: ScalingLazyListState,
    onClickRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScalingLazyColumn(
            modifier = modifier
                .weight(1f),
            state = scalingLazyListState,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            autoCentering = AutoCenteringParams(0)
        ) {
            when (status) {
                StopDetailsApiStatus.DONE -> {
                    // Dynamically set label width, to balance short vs long destination names
                    // TODO: Figure out how to do this via UI by wrapping content, auto centering, and scaling up
                    //  to a maxWdith
                    val departureLenths =
                        departures.map { departure -> departure.destination.length }
                    val maxDepartureLength = departureLenths.maxOrNull() ?: 0
                    val labelProportion = when (maxDepartureLength) {
                        in 0..6 -> 0.5f
                        in 7..8 -> 0.55f
                        in 9..10 -> 0.6f
                        else -> 0.66f
                    }
                    val waitProportion = 1f - labelProportion

                    items(departures) { departure ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                modifier = Modifier.fillParentMaxWidth(labelProportion),
                                text = departure.destination,
                                maxLines = 1,
                                textAlign = TextAlign.Right,
                                overflow = TextOverflow.Ellipsis
                            )
                            Row(
                                modifier = Modifier.fillParentMaxWidth(waitProportion),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = departure.wait.toString(),
                                    textAlign = TextAlign.Right,
                                    style = MaterialTheme.typography.body2.copy(fontFeatureSettings = "tnum"),
                                    fontWeight = FontWeight.Black,
                                    color = MaterialTheme.colors.primary
                                )
                                Text(
                                    // This is an abbreviation. How do I describe it?
                                    text = "m",
                                    textAlign = TextAlign.Left,
                                    fontSize = 12.sp,
                                )
                            }
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (lastUpdated != null) {
                Text(
                    text = String.format("Updated: %s", lastUpdated),
                    style = MaterialTheme.typography.caption3,
                )
            }
            CompactChip(
                onClick = { onClickRefresh() },
                label = { Text(text = "Refresh") },
                colors = ChipDefaults.secondaryChipColors()
            )
        }
    }
}

@WearDevicePreview
@Composable
fun StopDetailsColumnPreview() {
    val departures = listOf(
        MetrolinkStopDetail.DepartureEntry(
            destination = "Altrincham",
            type = Carriages.DOUBLE,
            status = Status.DUE,
            wait = (0..25).random(),
        ),
        MetrolinkStopDetail.DepartureEntry(
            destination = "Manchester Airport",
            type = Carriages.SINGLE,
            status = Status.DUE,
            wait = (0..25).random(),
        ),
        MetrolinkStopDetail.DepartureEntry(
            destination = "Rochdale Town Centre",
            type = Carriages.SINGLE,
            status = Status.DUE,
            wait = (0..25).random(),
        ),
        MetrolinkStopDetail.DepartureEntry(
            destination = "St Peter's Square",
            type = Carriages.DOUBLE,
            status = Status.DUE,
            wait = (0..25).random(),
        ),
        MetrolinkStopDetail.DepartureEntry(
            destination = "See Tram Front",
            type = Carriages.DOUBLE,
            status = Status.DUE,
            wait = (0..25).random(),
        ),
        MetrolinkStopDetail.DepartureEntry(
            destination = "Long long long long long long long long long long",
            type = Carriages.DOUBLE,
            status = Status.DUE,
            wait = (0..25).random(),
        )
    )
    StopDetailsColumn(
        status = StopDetailsApiStatus.DONE,
        departures = departures.sortedBy { it.wait },
        scalingLazyListState = ScalingLazyListState(),
        lastUpdated = "blah",
        onClickRefresh = {}
    )
}

@WearDevicePreview
@Composable
fun StopDetailsColumnShortPreview() {
    val departures = listOf(
        MetrolinkStopDetail.DepartureEntry(
            destination = "123456",
            type = Carriages.DOUBLE,
            status = Status.DUE,
            wait = 1,
        ),
        MetrolinkStopDetail.DepartureEntry(
            destination = "Bury",
            type = Carriages.SINGLE,
            status = Status.DUE,
            wait = 23,
        ),
    )
    StopDetailsColumn(
        status = StopDetailsApiStatus.DONE,
        departures = departures.sortedBy { it.wait },
        scalingLazyListState = ScalingLazyListState(),
        lastUpdated = "blah",
        onClickRefresh = {}
    )
}