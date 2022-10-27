package dev.rjackson.metrolinkstops.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.*
import dev.rjackson.metrolinkstops.R
import dev.rjackson.metrolinkstops.presentation.StopsListApiStatus
import dev.rjackson.metrolinkstops.presentation.StopsListViewModel
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun LinesList(
    modifier: Modifier = Modifier,
    scalingLazyListState: ScalingLazyListState,
    viewModel: StopsListViewModel = viewModel(),
    onLineClick: (String) -> Unit,
    onSettingsClick: () -> Unit
) {
    LaunchedEffect(Unit, block = {
        viewModel.getMetrolinks()
    })

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
        when (viewModel.status) {
            StopsListApiStatus.DONE -> {
                items(viewModel.stops) { stop ->
                    Chip(
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stop.stationLocation,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        colors = ChipDefaults.secondaryChipColors(),
                        onClick = { onLineClick(stop.stationLocation) }
                    )
                }
            }
            StopsListApiStatus.LOADING -> {
                item { Text(stringResource(R.string.loading)) }
            }
            StopsListApiStatus.ERROR -> {
                item { Text(stringResource(R.string.error)) }
            }
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
fun LinesListPreview() {
    LinesList(
        scalingLazyListState = rememberScalingLazyListState(),
        viewModel = StopsListViewModel(),
        onLineClick = {},
        onSettingsClick = {}
    )
}