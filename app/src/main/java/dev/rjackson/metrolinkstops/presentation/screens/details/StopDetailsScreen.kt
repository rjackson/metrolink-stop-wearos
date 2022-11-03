@file:OptIn(ExperimentalPagerApi::class)

package dev.rjackson.metrolinkstops.presentation.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.android.horologist.compose.navscaffold.scrollableColumn
import com.google.android.horologist.compose.pager.PagerScreen
import dev.rjackson.metrolinkstops.presentation.components.DeparturesList
import dev.rjackson.metrolinkstops.presentation.components.RefreshButton
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun StopDetailsScreen(
    modifier: Modifier = Modifier,
    stationLocation: String,
    viewModel: StopDetailsViewModel = viewModel(),
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
    pagerState: PagerState = rememberPagerState(),
) {
    LaunchedEffect(Unit) {
        refreshDepartures(stationLocation)
    }

    StopDetailsScaffold(
        modifier = modifier,
        title = stationLocation,
        onRefresh = { refreshDepartures(stationLocation) }
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
            is StopDetailsUiState.Success -> {
                PagerScreen(
                    count = uiState.stopDetail.messages.size + 1,
                    state = pagerState
                ) { page ->
                    when (page) {
                        0 -> StopDetailsDeparturesPage(
                            uiState = uiState,
                            onRefresh = { refreshDepartures(stationLocation) }
                        )
                        else -> MessagePage(message = uiState.stopDetail.messages[page - 1])
                    }
                }
            }
        }
    }
}

@Composable
fun StopDetailsDeparturesPage(
    modifier: Modifier = Modifier,
    uiState: StopDetailsUiState.Success,
    onRefresh: () -> Unit,
) {
    Box(modifier = modifier) {
        DeparturesList(
            stopDetail = uiState.stopDetail
        )

        RefreshButton(
            onClick = onRefresh,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        )
    }
}

@Composable
fun MessagePage(
    modifier: Modifier = Modifier,
    message: String,
    focusRequester: FocusRequester = FocusRequester(),
    scrollableState: ScalingLazyListState = rememberScalingLazyListState()
) {
//    Can't request focus yet because it triggers a page transition from departures to message??
//    TODO: Fix??
//    LaunchedEffect(Unit) {
//        focusRequester.requestFocus()
//    }

//    TODO: why does it start at the bottom of the mesage????

    // Pad by a third on round screens, to allow scrolling long lines near the center of the display
    val verticalContentPadding: Dp =
        if (LocalConfiguration.current.isScreenRound) Dp(LocalConfiguration.current.screenHeightDp.toFloat() / 3f) else 24.dp

    ScalingLazyColumn(
        modifier = modifier
            .fillMaxSize()
            .scrollableColumn(
                focusRequester = focusRequester,
                scrollableState = scrollableState
            )
            .scrollableColumn(focusRequester, scrollableState),
        anchorType = ScalingLazyListAnchorType.ItemStart,
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = verticalContentPadding),
        state = scrollableState,
    ) {
        item {
            Text(
                text = message
            )
        }
    }
}


@WearDevicePreview
@Composable
fun StopDetailsScreenPreview() {
    StopDetailsScreen(
        stationLocation = "Altrincham",
        uiState = previewUiState_Success,
        refreshDepartures = {},
    )
}

@WearDevicePreview
@Composable
fun StopDetailsScreenMessagePreview() {
    StopDetailsScreen(
        stationLocation = "Altrincham",
        uiState = previewUiState_Success,
        refreshDepartures = {},
        pagerState = PagerState(1)
    )
}

@WearDevicePreview
@Composable
fun StopDetailsScreenLoadingPreview() {
    StopDetailsScreen(
        stationLocation = "Altrincham",
        uiState = previewUiState_Loading,
        refreshDepartures = {},
    )
}

@WearDevicePreview
@Composable
fun StopDetailsScreenErrorPreview() {
    StopDetailsScreen(
        stationLocation = "Altrincham",
        uiState = previewUiState_Error,
        refreshDepartures = {},
    )
}