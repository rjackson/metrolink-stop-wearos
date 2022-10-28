package dev.rjackson.metrolinkstops.network.metrolinkstops

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.rjackson.metrolinkstops.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*


/**
 * @link https://github.com/rjackson/metrolink-stop
 */

private val BASE_URL = BuildConfig.METROLINK_STOPS_BASE_URL

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
    .build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()


interface MetrolinkStopsService {
    @GET("stop/{name}")
    suspend fun getStop(
        @Path(value = "name") name: String
    ): MetrolinkStopDetail
}

object MetrolinkStopsApi {
    val retrofitService: MetrolinkStopsService by lazy {
        retrofit.create(MetrolinkStopsService::class.java)
    }
}