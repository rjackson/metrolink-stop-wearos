package dev.rjackson.metrolinkstops.presentation.screens.details

import dev.rjackson.metrolinkstops.network.metrolinkstops.Carriages
import dev.rjackson.metrolinkstops.network.metrolinkstops.MetrolinkStopDetail
import dev.rjackson.metrolinkstops.network.metrolinkstops.Status
import java.util.*


val lotsOfDepartures = listOf(
    MetrolinkStopDetail.DepartureEntry(
        destination = "Altrincham",
        type = Carriages.DOUBLE,
        status = Status.DUE,
        wait = (0..25).random(),
    ),
    MetrolinkStopDetail.DepartureEntry(
        destination = "Manchester Airport",
        type = Carriages.SINGLE,
        status = Status.DUE,
        wait = (0..25).random(),
    ),
    MetrolinkStopDetail.DepartureEntry(
        destination = "Rochdale Town Centre",
        type = Carriages.SINGLE,
        status = Status.DUE,
        wait = (0..25).random(),
    ),
    MetrolinkStopDetail.DepartureEntry(
        destination = "St Peter's Square",
        type = Carriages.DOUBLE,
        status = Status.DUE,
        wait = (0..25).random(),
    ),
    MetrolinkStopDetail.DepartureEntry(
        destination = "See Tram Front",
        type = Carriages.DOUBLE,
        status = Status.DUE,
        wait = (0..25).random(),
    ),
    MetrolinkStopDetail.DepartureEntry(
        destination = "Long long long long long long long long long long",
        type = Carriages.DOUBLE,
        status = Status.DUE,
        wait = (0..25).random(),
    ),
    MetrolinkStopDetail.DepartureEntry(
        destination = "See Tram Front",
        type = Carriages.DOUBLE,
        status = Status.DUE,
        wait = (0..25).random(),
    ),
    MetrolinkStopDetail.DepartureEntry(
        destination = "Long long long long long long long long long long",
        type = Carriages.DOUBLE,
        status = Status.DUE,
        wait = (0..25).random(),
    )
)

val someMessages = listOf(
    "Pigeons on the tracks!",
    "Football's a kickin'"
)

val previewUiState_Success = StopDetailsUiState.Success(
    stopDetail = MetrolinkStopDetail(
        name = "Altrincham",
        departures = lotsOfDepartures,
        messages = someMessages,
        lastUpdated = Date()
    )
)
val previewUiState_Loading = StopDetailsUiState.Loading
val previewUiState_Error = StopDetailsUiState.Error