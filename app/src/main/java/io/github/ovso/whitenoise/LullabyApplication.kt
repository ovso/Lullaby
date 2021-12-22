package io.github.ovso.whitenoise

import android.app.Application
import io.github.ovso.whitenoise.data.AppContainer
import io.github.ovso.whitenoise.data.AppContainerImpl
import io.github.ovso.whitenoise.player.LullabyPlayer
import io.github.ovso.whitenoise.player.LullabyPlayerImpl

class LullabyApplication : Application() {
    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer
    lateinit var player: LullabyPlayer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
        player = LullabyPlayerImpl(this)
    }
}