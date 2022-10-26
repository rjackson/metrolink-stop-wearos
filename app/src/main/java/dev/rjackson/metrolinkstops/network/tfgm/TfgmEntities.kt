package dev.rjackson.metrolinkstops.network.tfgm

import com.squareup.moshi.Json

enum class Direction {
    @Json(name = "Incoming")
    INCOMING,

    @Json(name = "Outgoing")
    OUTGOING,

    @Json(name = "Incoming/Outgoing")
    BOTH,

    // TODO: How to make sure these are null in the first place??
    @Json(name = "")
    NULL
}

enum class Carriages {
    @Json(name = "Single")
    SINGLE,

    @Json(name = "Double")
    DOUBLE,

    // TODO: How to make sure these are null in the first place??
    @Json(name = "")
    NULL
}

enum class Status {
    @Json(name = "Due")
    DUE,

    @Json(name = "Departing")
    DEPARTING,

    @Json(name = "Arrived")
    ARRIVED,

    // TODO: How to make sure these are null in the first place??
    @Json(name = "")
    NULL
}

data class Metrolink(
    @Json(name = "Id") val id: Int,
    @Json(name = "Line") val Line: String,
    @Json(name = "TLAREF") val TLAREF: String,
    @Json(name = "PIDREF") val PIDREF: String,
    @Json(name = "StationLocation") val StationLocation: String,
    @Json(name = "AtcoCode") val AtcoCode: String,
    @Json(name = "Direction") val Direction: Direction,

    @Json(name = "Dest0") val Dest0: String?,
    @Json(name = "Carriages0") val Carriages0: Carriages?,
    @Json(name = "Status0") val Status0: Status?,
    @Json(name = "Wait0") val Wait0: String?,

    @Json(name = "Dest1") val Dest1: String?,
    @Json(name = "Carriages1") val Carriages1: Carriages?,
    @Json(name = "Status1") val Status1: Status?,
    @Json(name = "Wait1") val Wait1: String?,

    @Json(name = "Dest2") val Dest2: String?,
    @Json(name = "Carriages2") val Carriages2: Carriages?,
    @Json(name = "Status2") val Status2: Status?,
    @Json(name = "Wait2") val Wait2: String?,

    @Json(name = "Dest3") val Dest3: String?,
    @Json(name = "Carriages3") val Carriages3: Carriages?,
    @Json(name = "Status3") val Status3: Status?,
    @Json(name = "Wait3") val Wait3: String?,

    @Json(name = "MessageBoard") val MessageBoard: String?,
    @Json(name = "LastUpdated") val LastUpdated: String
)

