package dev.rjackson.metrolinkstops.presentation.screens.list

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.*
import dev.rjackson.metrolinkstops.R
import dev.rjackson.metrolinkstops.data.Stop
import dev.rjackson.metrolinkstops.presentation.components.StarCheckbox
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun StopsListScreen(
    modifier: Modifier = Modifier,
    scalingLazyListState: ScalingLazyListState,
    viewModel: StopsListViewModel = viewModel(factory = StopsListViewModel.Factory),
    onLineClick: (Stop) -> Unit,
    onSettingsClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    StopsList(
        allStops = uiState.allStops,
        scalingLazyListState = scalingLazyListState,
        modifier = modifier,
        onLineClick = onLineClick,
        onSettingsClick = onSettingsClick,
        onFavouriteChange = { stop, checked ->
            viewModel.onFavouriteChange(stop, checked)
        }
    )
}

@Composable
fun StopsList(
    allStops: List<Stop>,
    scalingLazyListState: ScalingLazyListState,
    modifier: Modifier = Modifier,
    onLineClick: (Stop) -> Unit,
    onFavouriteChange: (Stop, Boolean) -> Unit,
    onSettingsClick: () -> Unit
) {
    ScalingLazyColumn(
        modifier = modifier.fillMaxSize(),
        state = scalingLazyListState,
        autoCentering = AutoCenteringParams(itemIndex = 1)
    ) {
        item {
            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.title3
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
        items(allStops) { stop ->
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

        item {
            Spacer(modifier = Modifier.height(24.dp))
            CompactChip(
                onClick = { onSettingsClick() },
                colors = ChipDefaults.secondaryChipColors(),
                label = { Text(text = "Settings") }
            )
        }
    }
}

@WearDevicePreview
@Composable
fun StopsListScreenPreview() {
    val allStops = listOf(
        Stop("Altrincham"),
        Stop("Navigation Road"),
        Stop("Timperley"),
        Stop("Brooklands"),
        Stop("Sale"),
    )
    StopsList(
        allStops = allStops,
        scalingLazyListState = rememberScalingLazyListState(),
        onLineClick = {},
        onFavouriteChange = { _, _ -> },
        onSettingsClick = {}
    )
}