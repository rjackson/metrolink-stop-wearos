package dev.rjackson.metrolinkstops.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors

val Yellow200 = Color(0xfffff59d)
val Yellow500 = Color(0xffffeb3b)
val Yellow700 = Color(0xfffbc02d)
val Teal200 = Color(0xFF03DAC5)
val Red400 = Color(0xFFCF6679)

internal val wearColorPalette: Colors = Colors(
    primary = Yellow200,
    primaryVariant = Yellow700,
    secondary = Teal200,
    secondaryVariant = Teal200,
    error = Red400,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onError = Color.Black
)