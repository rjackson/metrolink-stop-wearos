package dev.rjackson.metrolinkstops.network.metrolinkstops

import com.squareup.moshi.Json
import dev.rjackson.metrolinkstops.network.tfgm.Carriages
import dev.rjackson.metrolinkstops.network.tfgm.Status

data class MetrolinkStopsEntry(
    @Json(name = "StationLocation") val stationLocation: String,
    @Json(name = "Line") val line: String,
)

data class MetrolinkStopDetail(
    val name: String,
    val departures: List<DepartureEntry>,
    val messages: List<String>,
    val lastUpdated: String,
) {
    data class DepartureEntry(
        val destination: String,
        val type: Carriages,
        val status: Status,
        val wait: Number
    )
}
