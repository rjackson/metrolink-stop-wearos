package dev.rjackson.metrolinkstops

import android.app.Application
import dev.rjackson.metrolinkstops.data.StopsRepo

class MyApplication : Application() {
    lateinit var stopsRepo: StopsRepo

    override fun onCreate() {
        super.onCreate()
        stopsRepo = StopsRepo(this)
    }
}