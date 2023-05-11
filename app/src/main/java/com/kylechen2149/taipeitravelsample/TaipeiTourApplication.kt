package com.kylechen2149.taipeitravelsample

import android.app.Application
import com.kylechen2149.taipeitravelsample.di.koinModules
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class TaipeiTourApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
        startKoin {
            androidContext(this@TaipeiTourApplication)
            modules(koinModules)
        }
    }

    private fun initLogger() {
        Logger.addLogAdapter(AndroidLogAdapter())
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                Logger.log(priority, tag, message, t)
            }
        })
    }
}