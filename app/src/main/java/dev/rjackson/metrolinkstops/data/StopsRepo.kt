package dev.rjackson.metrolinkstops.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.rjackson.metrolinkstops.data.Stop.Companion.toStop
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "favourites")

class StopsRepo(
    private val context: Context
) {
    fun getFavoriteStops(): Flow<List<Stop>> = context.dataStore.data.map { preferences ->
        preferences.asMap().values.map { stop ->
            (stop as String).toStop()
        }
    }

    suspend fun favouriteStop(stop: Stop) {
        context.dataStore.edit {
            it[stringPreferencesKey(stop.name)] = stop.toPreferenceString()
        }
    }

    suspend fun unFavouriteStop(stop: Stop) {
        context.dataStore.edit {
            it.remove(stringPreferencesKey(stop.name))
        }
    }

    // This is probably unnecessarily overcomplicated in ways I don't understand. I bet this is
    // extremely standard stuff for Android devs, but oh boy did I struggle getting to this point
    fun getStops(): Flow<List<Stop>> =
        getFavoriteStops().map { favouriteStops: List<Stop> ->
            val favouriteStopNames: Map<String, Boolean> =
                favouriteStops.map { it.name to true }.toMap()
            knownStops.map { stop ->
                Stop(
                    name = stop.name,
                    favourite = favouriteStopNames[stop.name] ?: false
                )
            }
        }


    companion object {
        val knownStops = listOf(
            Stop(name = "Manchester Airport"),
            Stop(name = "Baguley"),
            Stop(name = "Benchill"),
            Stop(name = "Barlow Moor Road"),
            Stop(name = "Crossacres"),
            Stop(name = "Martinscroft"),
            Stop(name = "Moor Road"),
            Stop(name = "Northern Moor"),
            Stop(name = "Peel Hall"),
            Stop(name = "Robinswood Road"),
            Stop(name = "Roundthorn"),
            Stop(name = "Shadowmoss"),
            Stop(name = "Sale Water Park"),
            Stop(name = "Wythenshawe Park"),
            Stop(name = "Wythenshawe Town Centre"),
            Stop(name = "Altrincham"),
            Stop(name = "Brooklands"),
            Stop(name = "Dane Road"),
            Stop(name = "Deansgate - Castlefield"),
            Stop(name = "Navigation Road"),
            Stop(name = "Old Trafford"),
            Stop(name = "Sale"),
            Stop(name = "Shudehill"),
            Stop(name = "Stretford"),
            Stop(name = "Trafford Bar"),
            Stop(name = "Timperley"),
            Stop(name = "Victoria"),
            Stop(name = "Abraham Moss"),
            Stop(name = "Bowker Vale"),
            Stop(name = "Besses O’ Th’ Barn"),
            Stop(name = "Bury"),
            Stop(name = "Crumpsall"),
            Stop(name = "Heaton Park"),
            Stop(name = "Market Street"),
            Stop(name = "Piccadilly Gardens"),
            Stop(name = "Piccadilly"),
            Stop(name = "Prestwich"),
            Stop(name = "Queens Road"),
            Stop(name = "Radcliffe"),
            Stop(name = "Whitefield"),
            Stop(name = "Ashton-Under-Lyne"),
            Stop(name = "Ashton Moss"),
            Stop(name = "Ashton West"),
            Stop(name = "Audenshaw"),
            Stop(name = "Cemetery Road"),
            Stop(name = "Clayton Hall"),
            Stop(name = "Droylsden"),
            Stop(name = "Edge Lane"),
            Stop(name = "Holt Town"),
            Stop(name = "New Islington"),
            Stop(name = "Etihad Campus"),
            Stop(name = "Velopark"),
            Stop(name = "Anchorage"),
            Stop(name = "Broadway"),
            Stop(name = "Cornbrook"),
            Stop(name = "Eccles"),
            Stop(name = "Exchange Quay"),
            Stop(name = "Exchange Square"),
            Stop(name = "Harbour City"),
            Stop(name = "Ladywell"),
            Stop(name = "Langworthy"),
            Stop(name = "MediaCityUK"),
            Stop(name = "Pomona"),
            Stop(name = "Salford Quays"),
            Stop(name = "St Peter's Square"),
            Stop(name = "Weaste"),
            Stop(name = "Central Park"),
            Stop(name = "Derker"),
            Stop(name = "Failsworth"),
            Stop(name = "Freehold"),
            Stop(name = "Hollinwood"),
            Stop(name = "Kingsway Business Park"),
            Stop(name = "Milnrow"),
            Stop(name = "Monsall"),
            Stop(name = "Newbold"),
            Stop(name = "Newhey"),
            Stop(name = "Newton Heath and Moston"),
            Stop(name = "Oldham Central"),
            Stop(name = "Oldham King Street"),
            Stop(name = "Oldham Mumps"),
            Stop(name = "Rochdale Railway Station"),
            Stop(name = "Rochdale Town Centre"),
            Stop(name = "Shaw and Crompton"),
            Stop(name = "South Chadderton"),
            Stop(name = "Westwood"),
            Stop(name = "Burton Road"),
            Stop(name = "Chorlton"),
            Stop(name = "Didsbury Village"),
            Stop(name = "East Didsbury"),
            Stop(name = "Firswood"),
            Stop(name = "St Werburgh’s Road"),
            Stop(name = "West Didsbury"),
            Stop(name = "Withington"),
            Stop(name = "Barton Dock Road"),
            Stop(name = "Imperial War Museum"),
            Stop(name = "Parkway"),
            Stop(name = "The Trafford Centre"),
            Stop(name = "Village"),
            Stop(name = "Wharfside")
        )
    }
}

