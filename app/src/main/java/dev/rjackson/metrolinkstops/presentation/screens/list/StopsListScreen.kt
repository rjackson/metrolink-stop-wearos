package dev.rjackson.metrolinkstops.presentation.screens.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.*
import dev.rjackson.metrolinkstops.data.Stop
import dev.rjackson.metrolinkstops.presentation.components.StopsList

@Composable
fun StopsListScreen(
    modifier: Modifier = Modifier,
    scalingLazyListState: ScalingLazyListState,
    viewModel: StopsListViewModel = viewModel(factory = StopsListViewModel.Factory),
    onLineClick: (Stop) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    StopsList(
        title = "All stops",
        stops = uiState.allStops,
        scalingLazyListState = scalingLazyListState,
        modifier = modifier,
        onLineClick = onLineClick,
        onFavouriteChange = { stop, checked ->
            viewModel.onFavouriteChange(stop, checked)
        }
    )
}