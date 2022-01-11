package io.github.ovso.lullaby

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.ovso.lullaby.data.AppContainer
import io.github.ovso.lullaby.data.AppContainerImpl
import io.github.ovso.lullaby.player.LullabyPlayer
import io.github.ovso.lullaby.player.LullabyPlayerImpl

@HiltAndroidApp
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
