package dev.rjackson.metrolinkstops.network.metrolinkstops

import com.squareup.moshi.Json

enum class Carriages {
    @Json(name = "Single")
    SINGLE,

    @Json(name = "Double")
    DOUBLE
}

enum class Status {
    @Json(name = "Due")
    DUE,

    @Json(name = "Departing")
    DEPARTING,

    @Json(name = "Arrived")
    ARRIVED
}

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
        val wait: Int
    )
}
