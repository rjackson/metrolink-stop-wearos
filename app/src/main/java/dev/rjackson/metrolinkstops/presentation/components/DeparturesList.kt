package dev.rjackson.metrolinkstops.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.google.android.horologist.compose.navscaffold.scrollableColumn
import dev.rjackson.metrolinkstops.network.metrolinkstops.MetrolinkStopDetail


@Composable
fun DeparturesList(
    stopDetail: MetrolinkStopDetail,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = FocusRequester(),
    scalingLazyListState: ScalingLazyListState = rememberScalingLazyListState(),
) {
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    with(stopDetail) {
        ScalingLazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 24.dp, bottom = 24.dp)
                .scrollableColumn(
                    focusRequester = focusRequester,
                    scrollableState = scalingLazyListState
                ),
            state = scalingLazyListState
        ) {

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

            item {
                ListHeader() {
                    Text(text = "Departures")
//                    TODO: Add last updated here maybe??
                }
            }

            if (departures.isEmpty()) {
                item { Text(text = "(No departures listed)") }
            } else {
                items(departures) { departure ->
                    DepartureRow(
                        departure = departure,
                        labelProportion = labelProportion
                    )
                }
            }
        }
    }

    PositionIndicator(
        scalingLazyListState = scalingLazyListState
    )
}