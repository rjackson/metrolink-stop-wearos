package dev.rjackson.metrolinkstops.presentation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.wear.compose.material.TimeText

class MyNavScaffoldViewModel : ViewModel() {
    private val defaultTimeText: @Composable (Modifier) -> Unit = { TimeText(modifier = it) }

    var timeText: @Composable (Modifier) -> Unit by mutableStateOf(
        defaultTimeText
    )
}
