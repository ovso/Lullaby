package io.github.ovso.lullaby

import android.app.Application
import io.github.ovso.lullaby.data.AppContainer
import io.github.ovso.lullaby.data.AppContainerImpl
import io.github.ovso.lullaby.player.LullabyPlayer
import io.github.ovso.lullaby.player.LullabyPlayerImpl

class LullabyApplication : Application() {
  lateinit var container: AppContainer
  lateinit var player: LullabyPlayer

  override fun onCreate() {
    super.onCreate()
    container = AppContainerImpl(this)
    player = LullabyPlayerImpl(this)
  }
}
