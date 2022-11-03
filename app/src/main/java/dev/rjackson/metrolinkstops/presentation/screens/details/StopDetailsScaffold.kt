package dev.rjackson.metrolinkstops.presentation.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import dev.rjackson.metrolinkstops.presentation.components.TitledTimeText
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun StopDetailsScaffold(
    title: String,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    footerMessage: String? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        content()

        // Overlays
        TitledTimeText(text = title)
//        if (footerMessage != null) {
//            CurvedFooterMessage(
//                text = footerMessage,
//                anchor = 90f,
//                angularDirection = CurvedDirection.Angular.Reversed,
//            )
//        }
    }
}

@WearDevicePreview
@Composable
fun StopDetailsScaffoldPreview() {
    StopDetailsScaffold(
        title = "Hello world",
        onRefresh = { },
        footerMessage = "I am a footer"
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "👋"
        )
    }
}