package dev.rjackson.metrolinkstops.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonColors
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon

@Composable
fun RefreshButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.secondaryButtonColors()
) {
    Button(
        modifier = modifier
            .size(ButtonDefaults.DefaultIconSize),
        onClick = onClick,
        colors = colors,
    ) {
        Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "Refresh")
    }
}