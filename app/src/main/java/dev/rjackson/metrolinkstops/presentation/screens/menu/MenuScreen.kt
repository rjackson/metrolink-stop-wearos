package dev.rjackson.metrolinkstops.presentation.screens.menu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Tram
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.material.*
import dev.rjackson.metrolinkstops.R
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    scalingLazyListState: ScalingLazyListState,
    onFavouritesClick: () -> Unit,
    onStopsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    ScalingLazyColumn(
        modifier = modifier.fillMaxSize(),
        state = scalingLazyListState
    ) {
        item {
            ListHeader(
                contentColor = MaterialTheme.colors.primary,
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                )
            }
        }

        item {
            Chip(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onFavouritesClick() },
                colors = ChipDefaults.secondaryChipColors(),
                label = { Text(textAlign = TextAlign.Center, text = "Favourites") },
                icon = {
                    Icon(imageVector = Icons.Rounded.Star, contentDescription = null)
                }
            )
        }

        item {
            Chip(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onStopsClick() },
                colors = ChipDefaults.secondaryChipColors(),
                label = { Text(textAlign = TextAlign.Center, text = "All stops") },
                icon = {
                    Icon(imageVector = Icons.Rounded.Tram, contentDescription = null)
                }
            )
        }

        item {
            Chip(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onSettingsClick() },
                colors = ChipDefaults.secondaryChipColors(),
                label = { Text(textAlign = TextAlign.Center, text = "About") },
                icon = {
                    Icon(imageVector = Icons.Rounded.Info, contentDescription = null)
                }
            )
        }
    }
}

@WearDevicePreview
@Composable
fun MenuScreenPreview() {
    MenuScreen(
        scalingLazyListState = rememberScalingLazyListState(),
        onFavouritesClick = {},
        onStopsClick = {},
        onSettingsClick = {},
    )
}