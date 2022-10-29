package dev.rjackson.metrolinkstops.presentation

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.google.android.horologist.compose.navscaffold.WearNavScaffold
import com.google.android.horologist.compose.navscaffold.scalingLazyColumnComposable
import com.google.android.horologist.compose.navscaffold.scrollStateComposable
import com.google.android.horologist.compose.navscaffold.scrollableColumn
import dev.rjackson.metrolinkstops.presentation.screens.list.StopsListScreen
import dev.rjackson.metrolinkstops.presentation.screens.settings.SettingsScreen
import dev.rjackson.metrolinkstops.presentation.screens.details.StopDetailsScreen
import dev.rjackson.metrolinkstops.presentation.screens.favourites.FavStopsListScreen
import dev.rjackson.metrolinkstops.presentation.screens.menu.MenuScreen
import dev.rjackson.metrolinkstops.presentation.theme.MetrolinkStopsTheme

@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberSwipeDismissableNavController()

    val renderDefaultTimeText: @Composable (Modifier) -> Unit = { TimeText() }
    var renderTimeText by remember { mutableStateOf(renderDefaultTimeText) }


    MetrolinkStopsTheme {
        WearNavScaffold(
            modifier = modifier,
            navController = navController,
            startDestination = "menu",
            timeText = renderTimeText
        ) {
            scalingLazyColumnComposable(
                route = "menu",
                scrollStateBuilder = { ScalingLazyListState() }
            ) {
                renderTimeText = renderDefaultTimeText
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
                        navController.navigate("settings")
                    }
                )
            }

            scalingLazyColumnComposable(
                route = "fav_stops",
                scrollStateBuilder = { ScalingLazyListState() }
            ) {
                renderTimeText = renderDefaultTimeText
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
                renderTimeText = renderDefaultTimeText
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

            scalingLazyColumnComposable(
                route = "stop/{stationLocation}",
                scrollStateBuilder = { ScalingLazyListState() }
            ) {
                StopDetailsScreen(
                    modifier = Modifier
                        .scrollableColumn(
                            it.viewModel.focusRequester,
                            it.scrollableState
                        ),
                    stationLocation = it.backStackEntry.arguments?.getString("stationLocation")!!,
                    scalingLazyListState = it.scrollableState,
                    onSetTimeText = {
                        renderTimeText = it
                    }
                )
            }

            scrollStateComposable(
                route = "settings",
                scrollStateBuilder = { ScrollState(0) }
            ) {
                renderTimeText = renderDefaultTimeText
                SettingsScreen(
                    scrollableState = it.scrollableState,
                    focusRequester = it.viewModel.focusRequester
                )
            }
        }
    }
}