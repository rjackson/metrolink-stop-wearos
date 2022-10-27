package dev.rjackson.metrolinkstops.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

@Composable
fun StopDetails(
    modifier: Modifier = Modifier,
    stationLocation: String,
) {
    LaunchedEffect(Unit, block = {
//        viewModel.getMetrolinks()
    })

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stationLocation)
    }
}

@WearDevicePreview
@Composable
fun StopDetailsPreview() {
    StopDetails(stationLocation = "Good Station")
}