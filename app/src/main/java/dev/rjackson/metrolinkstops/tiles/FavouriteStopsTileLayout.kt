package dev.rjackson.metrolinkstops.tiles

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.wear.tiles.ActionBuilders
import androidx.wear.tiles.ColorBuilders.ColorProp
import androidx.wear.tiles.DeviceParametersBuilders
import androidx.wear.tiles.LayoutElementBuilders.Column
import androidx.wear.tiles.LayoutElementBuilders.Row
import androidx.wear.tiles.ModifiersBuilders
import androidx.wear.tiles.material.*
import androidx.wear.tiles.material.Typography.TYPOGRAPHY_TITLE3
import androidx.wear.tiles.material.layouts.PrimaryLayout
import com.google.android.horologist.compose.tools.LayoutRootPreview
import com.google.android.horologist.compose.tools.buildDeviceParameters
import dev.rjackson.metrolinkstops.data.Stop
import dev.rjackson.metrolinkstops.tools.WearDevicePreview

val emptyClickable = ModifiersBuilders.Clickable.Builder()
    .setOnClick(ActionBuilders.LoadAction.Builder().build())
    .setId("")
    .build()

internal fun favouriteStopsTileLayout(
    state: FavouriteStopsTileState,
    context: Context,
    deviceParameters: DeviceParametersBuilders.DeviceParameters
) = PrimaryLayout.Builder(deviceParameters)
    .setContent(
        Column.Builder()
//            .setModifiers(/* space between ?? */)
            .apply {
                addContent(titleRowLayout(context))

                state.stops.forEach { stop ->
                    addContent(stopChipLayout(context, stop, emptyClickable, deviceParameters))
                }
            }
            .build()
    ).setPrimaryChipContent(
        allStopsChipLayout(context, deviceParameters)
    )
    .build()

private fun allStopsChipLayout(
    context: Context,
    deviceParameters: DeviceParametersBuilders.DeviceParameters
) = CompactChip.Builder(
    context,
    "All stops",
    emptyClickable,
    deviceParameters
)
    .setChipColors(ChipColors.secondaryChipColors(FavouriteStopsTileTheme.colors))
    .build()

private fun titleRowLayout(
    context: Context
) = Row.Builder()
    .addContent(
        Text.Builder(context, "Favourite stops")
            .setTypography(TYPOGRAPHY_TITLE3)
            .setColor(ColorProp.Builder().setArgb(FavouriteStopsTileTheme.colors.primary).build())
            .build()
    )
    .build()


private fun stopChipLayout(
    context: Context,
    stop: Stop,
    clickable: ModifiersBuilders.Clickable,
    deviceParameters: DeviceParametersBuilders.DeviceParameters
) = Chip.Builder(context, clickable, deviceParameters)
    .setContentDescription(stop.name)
    .apply {
        setPrimaryLabelContent(stop.name)
        setChipColors(ChipColors.secondaryChipColors(FavouriteStopsTileTheme.colors))
    }
    .build()

@WearDevicePreview
@Composable
private fun MessageTilePreview() {
    val stops = listOf(
        Stop("Altrincham", true),
        Stop("Navigation Road", true),
        Stop("Timperley", true),
    )
    val context = LocalContext.current
    val state = FavouriteStopsTileState(stops)
    LayoutRootPreview(
        favouriteStopsTileLayout(
            state,
            context,
            buildDeviceParameters(context.resources)
        )
    ) {

    }
}