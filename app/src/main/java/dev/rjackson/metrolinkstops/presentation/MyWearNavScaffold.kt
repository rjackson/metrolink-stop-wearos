package dev.rjackson.metrolinkstops.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.navigation.SwipeDismissableNavHostState
import androidx.wear.compose.navigation.currentBackStackEntryAsState
import androidx.wear.compose.navigation.rememberSwipeDismissableNavHostState
import com.google.android.horologist.compose.navscaffold.WearNavScaffold

@Composable
fun MyWearNavScaffold(
    startDestination: String,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    snackbar: @Composable () -> Unit = {},
    state: SwipeDismissableNavHostState = rememberSwipeDismissableNavHostState(),
    builder: NavGraphBuilder.() -> Unit
) {
    val currentBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()

    val viewModel: MyNavScaffoldViewModel? = currentBackStackEntry?.let {
        // this instantiates a viewmodel on each nav change, thereby resetting timetext
        // (this is what I want) 🙂
        viewModel(viewModelStoreOwner = it)
    }

    WearNavScaffold(
        startDestination = startDestination,
        navController = navController,
        modifier = modifier,
        snackbar = snackbar,
        timeText = viewModel?.timeText ?: { TimeText(modifier = it) },
        state = state,
        builder = builder
    )
}