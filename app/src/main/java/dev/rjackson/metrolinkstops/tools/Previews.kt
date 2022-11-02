package dev.rjackson.metrolinkstops.tools

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    device = Devices.WEAR_OS_SMALL_ROUND,
    showSystemUi = true,
    backgroundColor = 0xff000000,
    showBackground = true,
    group = "Devices - Small Round",
)
@Preview(
    device = Devices.WEAR_OS_SQUARE,
    showSystemUi = true,
    backgroundColor = 0xff000000,
    showBackground = true,
    group = "Devices - Small Round",
)
annotation class WearDevicePreview