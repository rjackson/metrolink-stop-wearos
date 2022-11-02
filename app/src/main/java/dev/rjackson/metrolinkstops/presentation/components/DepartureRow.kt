package dev.rjackson.metrolinkstops.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import dev.rjackson.metrolinkstops.network.metrolinkstops.Carriages
import dev.rjackson.metrolinkstops.network.metrolinkstops.MetrolinkStopDetail
import dev.rjackson.metrolinkstops.network.metrolinkstops.Status

@Composable
fun DepartureRow(
    departure: MetrolinkStopDetail.DepartureEntry,
    modifier: Modifier = Modifier,
    labelProportion: Float = 0.5f
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            8.dp
        ),
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(
                labelProportion
            ),
            text = departure.destination,
            maxLines = 1,
            textAlign = TextAlign.Right,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(
                2.dp
            )
        ) {
            Text(
                text = departure.wait.toString(),
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.body2.copy(
                    fontFeatureSettings = "tnum"
                ),
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colors.primary
            )
            Text(
                // This is an abbreviation. How do I describe it?
                text = "m",
                textAlign = TextAlign.Left,
                fontSize = 12.sp,
            )
        }
    }
}

@Preview(widthDp = 144)
@Composable
fun DepartureRowPreview(
) {
    DepartureRow(
        departure = MetrolinkStopDetail.DepartureEntry(
            destination = "See Tram Front",
            type = Carriages.DOUBLE,
            status = Status.DUE,
            wait = (0..25).random(),
        ),
    )
}