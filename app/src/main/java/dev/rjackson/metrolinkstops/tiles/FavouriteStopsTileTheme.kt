package dev.rjackson.metrolinkstops.tiles

import androidx.compose.ui.graphics.toArgb
import androidx.wear.compose.material.Colors
import dev.rjackson.metrolinkstops.presentation.theme.wearColorPalette

object FavouriteStopsTileTheme {
    val colors = composeColors.toTileColors()
}

/**
 * A Compose-based Colors object.
 *
 * This would typically be used in your Wear app too (and include more color overrides). Since it's
 * being used only for Tiles here, only the primary/surface colors are defined.
 */
private val composeColors = wearColorPalette

private fun Colors.toTileColors() = androidx.wear.tiles.material.Colors(
    /* primary = */ primary.toArgb(),
    /* onPrimary = */ onPrimary.toArgb(),
    /* surface = */ surface.toArgb(),
    /* onSurface = */ onSurface.toArgb()
)