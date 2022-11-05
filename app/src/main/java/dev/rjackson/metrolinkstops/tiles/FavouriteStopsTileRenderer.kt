package dev.rjackson.metrolinkstops.tiles

import android.content.Context
import androidx.wear.tiles.DeviceParametersBuilders
import androidx.wear.tiles.LayoutElementBuilders.LayoutElement
import com.google.android.horologist.tiles.ExperimentalHorologistTilesApi
import com.google.android.horologist.tiles.render.SingleTileLayoutRenderer
import dev.rjackson.metrolinkstops.data.Stop

@OptIn(ExperimentalHorologistTilesApi::class)
class FavouriteStopsTileRenderer(context: Context) :
    SingleTileLayoutRenderer<FavouriteStopsTileState, Stop>(context) {

    override fun renderTile(
        state: FavouriteStopsTileState,
        deviceParameters: DeviceParametersBuilders.DeviceParameters
    ): LayoutElement {
        return favouriteStopsTileLayout(state, context, deviceParameters)
    }
}