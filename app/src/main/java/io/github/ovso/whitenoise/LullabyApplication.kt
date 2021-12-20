package io.github.ovso.whitenoise

import android.app.Application
import io.github.ovso.whitenoise.data.AppContainer
import io.github.ovso.whitenoise.data.AppContainerImpl

class LullabyApplication : Application() {
    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}