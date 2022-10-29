package dev.rjackson.metrolinkstops.presentation.screens.favourites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.*
import dev.rjackson.metrolinkstops.data.Stop
import dev.rjackson.metrolinkstops.presentation.components.StopsList

@Composable
fun FavStopsListScreen(
    modifier: Modifier = Modifier,
    scalingLazyListState: ScalingLazyListState,
    viewModel: FavStopsListViewModel = viewModel(factory = FavStopsListViewModel.Factory),
    onLineClick: (Stop) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    StopsList(
        title = "Favourite stops",
        stops = uiState.stops,
        scalingLazyListState = scalingLazyListState,
        modifier = modifier,
        onLineClick = onLineClick,
        onFavouriteChange = { stop, checked ->
            viewModel.onFavouriteChange(stop, checked)
        }
    )
}