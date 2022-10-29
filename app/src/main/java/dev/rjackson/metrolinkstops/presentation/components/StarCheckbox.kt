package dev.rjackson.metrolinkstops.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*

@Composable
fun StarCheckbox(
    checked: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checkedColor: Color = MaterialTheme.colors.primary,
    uncheckedColor: Color = MaterialTheme.colors.secondary,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
            .requiredSize(24.dp)
            .then(
                if (onCheckedChange != null)
                    Modifier.toggleable(
                        enabled = enabled,
                        value = checked,
                        onValueChange = onCheckedChange,
                        role = Role.Checkbox
                    ) else Modifier
            )
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Favourite",
                tint = checkedColor
            )
        } else {
            Icon(
                imageVector = Icons.Filled.StarOutline,
                contentDescription = "Not favourite",
                tint = uncheckedColor
            )
        }
    }
}

@Preview(name = "Unchecked")
@Composable
fun StarCheckboxUncheckedPreview() {
    StarCheckbox(checked = false) {}
}

@Preview(name = "Checked")
@Composable
fun StarCheckboxCheckedPreview() {
    StarCheckbox(checked = true) {}
}