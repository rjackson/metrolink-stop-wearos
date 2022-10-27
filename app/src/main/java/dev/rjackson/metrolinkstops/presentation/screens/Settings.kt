package dev.rjackson.metrolinkstops.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun Settings(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
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
    Settings()
}