package com.raxors.otakuhub

import android.app.Application
import com.raxors.otakuhub.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class OtakuHubApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@OtakuHubApp)
            modules(appModule)
        }
    }

}