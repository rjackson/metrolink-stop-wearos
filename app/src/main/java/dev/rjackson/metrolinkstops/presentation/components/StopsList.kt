package dev.rjackson.metrolinkstops.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import dev.rjackson.metrolinkstops.data.Stop
import dev.rjackson.metrolinkstops.tools.WearDevicePreview


@Composable
fun StopsList(
    title: String,
    stops: List<Stop>,
    scalingLazyListState: ScalingLazyListState,
    modifier: Modifier = Modifier,
    onLineClick: (Stop) -> Unit,
    onFavouriteChange: (Stop, Boolean) -> Unit
) {
    ScalingLazyColumn(
        modifier = modifier.fillMaxSize(),
        state = scalingLazyListState,
        autoCentering = AutoCenteringParams(itemIndex = 1)
    ) {
        item {
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                text = title,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.title3
            )
        }
        items(stops) { stop ->
            val checked = stop.favourite
            SplitToggleChip(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stop.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = ToggleChipDefaults.splitToggleChipColors(),
                onClick = { onLineClick(stop) },
                checked = checked,
                onCheckedChange = { onFavouriteChange(stop, it) },
                toggleControl = {
                    StarCheckbox(
                        checked = checked,
                        modifier = Modifier.semantics {
                            this.contentDescription =
                                if (checked) "Add to favourites" else "Remove from favourites"
                        },
                        enabled = true
                    )
                },
            )
        }
    }
}

@WearDevicePreview
@Composable
fun StopsListScreenPreview() {
    val stops = listOf(
        Stop("Altrincham"),
        Stop("Navigation Road"),
        Stop("Timperley"),
        Stop("Brooklands"),
        Stop("Sale"),
    )
    StopsList(
        title = "Some stops",
        stops = stops,
        scalingLazyListState = rememberScalingLazyListState(),
        onLineClick = {},
        onFavouriteChange = { _, _ -> }
    )
}