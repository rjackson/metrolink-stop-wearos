package dev.rjackson.metrolinkstops.network.metrolinkstops

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.rjackson.metrolinkstops.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @link https://github.com/rjackson/metrolink-stop
 *
 *  TODO: Learn how to persist data. I.e. there won't be any new/old stops very often. No need to
 *  pull a fresh list of stops on every launch.
 */

private val BASE_URL = BuildConfig.METROLINK_STOPS_BASE_URL

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()


interface MetrolinkStopsService {
    @GET("stops")
    suspend fun getStops(): List<MetrolinkStopsEntry>

    @GET("stop/{name}")
    suspend fun getStop(
        @Path("name") name: String
    ): MetrolinkStopDetail
}

object MetrolinkStopsApi {
    val retrofitService: MetrolinkStopsService by lazy {
        retrofit.create(MetrolinkStopsService::class.java)
    }
}