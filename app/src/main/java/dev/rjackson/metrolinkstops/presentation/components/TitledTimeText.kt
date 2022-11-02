package dev.rjackson.metrolinkstops.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.wear.compose.foundation.CurvedTextStyle
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.curvedText

@Composable
fun TitledTimeText(
    text: String,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = MaterialTheme.typography.title3,
    titleColor: Color = MaterialTheme.colors.primary
) {
    TimeText(
        modifier = modifier,
        startCurvedContent = {
            curvedText(
                style = CurvedTextStyle(titleStyle),
                color = titleColor,
                text = text
            )
        },
        startLinearContent = {
            Text(
                style = titleStyle,
                color = titleColor,
                text = text
            )
        }
    )
}