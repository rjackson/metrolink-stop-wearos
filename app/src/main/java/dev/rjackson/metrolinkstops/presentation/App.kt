package dev.rjackson.metrolinkstops.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.rjackson.metrolinkstops.presentation.screens.LinesList
import dev.rjackson.metrolinkstops.presentation.screens.Settings
import dev.rjackson.metrolinkstops.presentation.screens.StopDetails
import dev.rjackson.metrolinkstops.presentation.theme.MetrolinkStopsTheme

@Composable
fun App(modifier: Modifier = Modifier, viewModel: AppViewModel = viewModel()) {
    val navController = rememberSwipeDismissableNavController()

    // Why does the wear-os-samples/ComposeAdvanced track this very complicatadly via a viewmodel?
    val scalingLazyListState: ScalingLazyListState = rememberScalingLazyListState()

    MetrolinkStopsTheme {
        Scaffold(
            modifier = modifier,
            timeText = {
                TimeText()
            },
            vignette = {
                if (viewModel.status == AppApiStatus.LOADING) {
                    Vignette(vignettePosition = VignettePosition.TopAndBottom)
                }
            },
            positionIndicator = {
                PositionIndicator(
                    scalingLazyListState = scalingLazyListState
                )
            }
        ) {
            SwipeDismissableNavHost(
                navController = navController,
                startDestination = "lines_list"
            ) {
                composable("lines_list") {
                    LinesList(
                        viewModel = viewModel,
                        scalingLazyListState = scalingLazyListState,
                        onLineClick = { stationLocation ->
                            navController.navigate("line_detail/$stationLocation")
                        },
                        onSettingsClick = {
                            navController.navigate("settings")
                        }
                    )
                }

                composable("line_detail/{stationLocation}") {
                    StopDetails(stationLocation = it.arguments?.getString("stationLocation")!!)
                }

                composable("settings") {
                    Settings()
                }
            }
        }
    }
}