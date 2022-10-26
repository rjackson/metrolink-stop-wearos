/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package dev.rjackson.metrolinkstops.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.*
import dev.rjackson.metrolinkstops.presentation.theme.MetrolinkStopsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = MetrolinksViewModel()

        super.onCreate(savedInstanceState)
        setContent {
            MetrolinksStopsApp(vm)
        }
    }
}

@Composable
fun MetrolinksStopsApp(vm: MetrolinksViewModel) {
    val scalingLazyListState: ScalingLazyListState = rememberScalingLazyListState()

    LaunchedEffect(Unit, block = {
        vm.getMetrolinks()
    })

    MetrolinkStopsTheme {
        Scaffold(
            timeText = {
                TimeText()
            },
            vignette = {
                if (vm.status == MetrolinksApiStatus.LOADING) {
                    Vignette(vignettePosition = VignettePosition.TopAndBottom)
                }
            },
            positionIndicator = {
                PositionIndicator(
                    scalingLazyListState = scalingLazyListState
                )
            }
        ) {
            ScalingLazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = scalingLazyListState,
                autoCentering = AutoCenteringParams(itemIndex = 1)
            ) {
                item {
                    Text(
                        text = "Metrolink Stops",
                        color = MaterialTheme.colors.primary
                    )
                }
                when (vm.status) {
                    MetrolinksApiStatus.DONE -> {
                        items(vm.stops) { stop ->
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
                                onClick = { { /*TODO*/ } }
                            )
                        }
                    }
                    MetrolinksApiStatus.LOADING -> {
                        item { Text("Loading") }
                    }
                    MetrolinksApiStatus.ERROR -> {
                        item { Text("Error: ${vm.errorMessage}") }
                    }
                }
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val vm = MetrolinksViewModel()

    MetrolinksStopsApp(vm)
}