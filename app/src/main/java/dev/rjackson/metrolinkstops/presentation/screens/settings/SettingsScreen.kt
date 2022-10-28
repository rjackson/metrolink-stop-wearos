package dev.rjackson.metrolinkstops.presentation.screens.settings

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.google.android.horologist.compose.navscaffold.scrollableColumn
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    scrollableState: ScrollState,
    focusRequester: FocusRequester
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = scrollableState)
            .scrollableColumn(
                focusRequester = focusRequester,
                scrollableState = scrollableState
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Rounded.Settings,
            contentDescription = "Settings"
        )
        Text(text = "Settings")
    }
}

@WearDevicePreview
@Composable
fun SettingsPreview() {
    SettingsScreen(
        scrollableState = ScrollState(0),
        focusRequester = FocusRequester()
    )
}