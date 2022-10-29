package dev.rjackson.metrolinkstops.presentation.screens.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Tram
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
        state = scalingLazyListState,
    ) {
        item {
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom=20.dp),
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.title3
            )
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
                label = { Text(textAlign = TextAlign.Center, text = "Settings") },
                icon = {
                    Icon(imageVector = Icons.Rounded.Settings, contentDescription = null)
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