package dev.rjackson.metrolinkstops.network.tfgm

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.rjackson.metrolinkstops.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


/**
 * TfgmApiService allows us to pull Metrolink-related data directly from TfGM's API.
 *
 * This will be useful for testing and development, but will need further work before being
 * production-ready:
 *
 * - Proxy requests via our own backend, to avoid exposing our TfGM API keys (this is the primary
 *   purpose tbh)
 * - Restrict requests to our app only? If possible
 *
 *  TODO: Set up custom backend before pushing live.
 *  TODO: Learn how to persist data. I.e. there won't be any new/old stops very often. No need to
 *  pull a fresh list of stops on every launch.
 */

private val BASE_URL = BuildConfig.TFGM_API_BASE_URL

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(TfgmJsonAdapterFactory())
    .build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()


interface TfgmApiService {
    @TfgmJson
    @Headers("Ocp-Apim-Subscription-Key: ${BuildConfig.TFGM_API_SUBSCRIPTION_KEY}")
    @GET("Metrolinks")
    suspend fun getAllMetrolinks(): List<Metrolink>

    @TfgmJson
    @Headers("Ocp-Apim-Subscription-Key: ${BuildConfig.TFGM_API_SUBSCRIPTION_KEY}")
    @GET("Metrolinks({id})")
    suspend fun getMetrolink(
        @Path("id") id: String
    ): Metrolink
}

object TfgmApi {
    val retrofitService: TfgmApiService by lazy {
        retrofit.create(TfgmApiService::class.java)
    }
}