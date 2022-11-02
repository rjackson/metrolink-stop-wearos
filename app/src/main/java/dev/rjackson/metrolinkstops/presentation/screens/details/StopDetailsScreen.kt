package dev.rjackson.metrolinkstops.presentation.screens.details

import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.wear.compose.material.Text
import dev.rjackson.metrolinkstops.presentation.components.DeparturesList
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun StopDetailsScreen(
    modifier: Modifier = Modifier,
    stationLocation: String,
    viewModel: StopDetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    StopDetailsScreen(
        modifier = modifier,
        stationLocation = stationLocation,
        uiState = uiState,
        refreshDepartures = viewModel::refreshDepartures
    )
}

@Composable
fun StopDetailsScreen(
    stationLocation: String,
    uiState: StopDetailsUiState,
    refreshDepartures: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) {
        refreshDepartures(stationLocation)
    }

    val footerMessage = when (uiState) {
        is StopDetailsUiState.Success -> String.format(
            "Last updated: %s",
            DateFormat.getTimeFormat(LocalContext.current)
                .format(uiState.stopDetail.lastUpdated)
        )
        is StopDetailsUiState.Loading -> "Refreshing..."
        is StopDetailsUiState.Error -> "Error loading stop info"
    }

    StopDetailsScaffold(
        modifier = modifier,
        title = stationLocation,
        onRefresh = { refreshDepartures(stationLocation) },
        footerMessage = footerMessage
    ) {
        when (uiState) {
            is StopDetailsUiState.Error -> Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Error"
            )
            is StopDetailsUiState.Loading -> Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Loading..."
            )
            is StopDetailsUiState.Success -> DeparturesList(
                stopDetail = uiState.stopDetail
            )
        }
    }
}

@WearDevicePreview
@Composable
fun StopDetailsPagerScreenPreview() {
    StopDetailsScreen(
        stationLocation = "Altrincham",
        uiState = previewUiState_Success,
        refreshDepartures = {},
    )
}

@WearDevicePreview
@Composable
fun StopDetailsPagerScreenLoadingPreview() {
    StopDetailsScreen(
        stationLocation = "Altrincham",
        uiState = previewUiState_Loading,
        refreshDepartures = {},
    )
}

@WearDevicePreview
@Composable
fun StopDetailsPagerScreenErrorPreview() {
    StopDetailsScreen(
        stationLocation = "Altrincham",
        uiState = previewUiState_Error,
        refreshDepartures = {},
    )
}