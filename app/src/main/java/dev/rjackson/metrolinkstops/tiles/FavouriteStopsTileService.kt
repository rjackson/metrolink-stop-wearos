@file:OptIn(ExperimentalHorologistTilesApi::class, ExperimentalHorologistTilesApi::class)

package dev.rjackson.metrolinkstops.tiles

import androidx.lifecycle.lifecycleScope
import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.RequestBuilders.TileRequest
import androidx.wear.tiles.ResourceBuilders
import androidx.wear.tiles.TileBuilders.Tile
import com.google.android.horologist.tiles.ExperimentalHorologistTilesApi
import com.google.android.horologist.tiles.SuspendingTileService
import dev.rjackson.metrolinkstops.data.StopsRepo
import kotlinx.coroutines.flow.*

class FavouriteStopsTileService : SuspendingTileService() {
    private lateinit var repo: StopsRepo
    private lateinit var renderer: FavouriteStopsTileRenderer
    private lateinit var tileStateFlow: StateFlow<FavouriteStopsTileState?>

    override fun onCreate() {
        super.onCreate()

        repo = StopsRepo(this)
        renderer = FavouriteStopsTileRenderer(this)

        tileStateFlow = repo.getFavoriteStops()
            .map { stops -> FavouriteStopsTileState(stops) }
            .stateIn(
                lifecycleScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )
    }

    override suspend fun tileRequest(requestParams: TileRequest): Tile {
        val tileState: FavouriteStopsTileState = latestTileState()
        return renderer.renderTimeline(tileState, requestParams)
    }

    private suspend fun latestTileState(): FavouriteStopsTileState {
        val tileState = tileStateFlow.filterNotNull().first()

        return tileState
    }

    override suspend fun resourcesRequest(
        requestParams: RequestBuilders.ResourcesRequest
    ): ResourceBuilders.Resources = ResourceBuilders.Resources.Builder().setVersion("1").build()
}
