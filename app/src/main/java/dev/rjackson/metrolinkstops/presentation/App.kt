package dev.rjackson.metrolinkstops.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.google.android.horologist.compose.navscaffold.*
import dev.rjackson.metrolinkstops.presentation.screens.details.StopDetailsScreen
import dev.rjackson.metrolinkstops.presentation.screens.favourites.FavStopsListScreen
import dev.rjackson.metrolinkstops.presentation.screens.info.InfoScreen
import dev.rjackson.metrolinkstops.presentation.screens.list.StopsListScreen
import dev.rjackson.metrolinkstops.presentation.screens.menu.MenuScreen
import dev.rjackson.metrolinkstops.presentation.theme.MetrolinkStopsTheme

@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberSwipeDismissableNavController()

    MetrolinkStopsTheme {
        WearNavScaffold(
            modifier = modifier,
            navController = navController,
            startDestination = "menu"
        ) {
            scalingLazyColumnComposable(
                route = "menu",
                scrollStateBuilder = { ScalingLazyListState() }
            ) {
                MenuScreen(
                    modifier = Modifier
                        .scrollableColumn(
                            it.viewModel.focusRequester,
                            it.scrollableState
                        ),
                    scalingLazyListState = it.scrollableState,
                    onFavouritesClick = {
                        navController.navigate("fav_stops")
                    },
                    onStopsClick = {
                        navController.navigate("all_stops")
                    },
                    onSettingsClick = {
                        navController.navigate("info")
                    }
                )
            }

            scalingLazyColumnComposable(
                route = "fav_stops",
                scrollStateBuilder = { ScalingLazyListState() }
            ) {
                FavStopsListScreen(
                    modifier = Modifier
                        .scrollableColumn(
                            it.viewModel.focusRequester,
                            it.scrollableState
                        ),
                    scalingLazyListState = it.scrollableState,
                    onLineClick = { stop ->
                        navController.navigate("stop/${stop.name}")
                    }
                )
            }

            scalingLazyColumnComposable(
                route = "all_stops",
                scrollStateBuilder = { ScalingLazyListState() }
            ) {
                StopsListScreen(
                    modifier = Modifier
                        .scrollableColumn(
                            it.viewModel.focusRequester,
                            it.scrollableState
                        ),
                    scalingLazyListState = it.scrollableState,
                    onLineClick = { stop ->
                        navController.navigate("stop/${stop.name}")
                    }
                )
            }

            wearNavComposable(
                route = "stop/{name}",
                arguments = listOf(navArgument("name") {
                    type = NavType.StringType
                })
            ) { backStack, viewModel ->
                // This screen will manage its own scaffold
                viewModel.timeTextMode = NavScaffoldViewModel.TimeTextMode.Off
                viewModel.positionIndicatorMode = NavScaffoldViewModel.PositionIndicatorMode.Off

                StopDetailsScreen(
                    stationLocation = backStack.arguments?.getString("name")!!
                )
            }

            scalingLazyColumnComposable(
                route = "info",
                scrollStateBuilder = { ScalingLazyListState() }
            ) {
                InfoScreen(
                    modifier = Modifier
                        .scrollableColumn(
                            it.viewModel.focusRequester,
                            it.scrollableState
                        ),
                    scalingLazyListState = it.scrollableState,
                )
            }
        }
    }
}