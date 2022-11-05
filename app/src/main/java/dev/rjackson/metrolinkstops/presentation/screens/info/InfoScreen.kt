package dev.rjackson.metrolinkstops.presentation.screens.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    scalingLazyListState: ScalingLazyListState = ScalingLazyListState(),
) {
    ScalingLazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = scalingLazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Icon(
                imageVector = Icons.Rounded.Info,
                contentDescription = "Info"
            )
        }

        item {
            Text(
                text = "metrolink.rjackson.dev",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary
            )
        }

        items(
            listOf(
                "github.com/rjackson/metrolink-stop-wearos",
                "Contains Transport for Greater Manchester data",
                "💛\nrjackson.dev"
            )
        ) {
            Text(
                text = it,
                textAlign = TextAlign.Center
            )
        }

    }
}

@WearDevicePreview
@Composable
fun SettingsPreview() {
    InfoScreen()
}