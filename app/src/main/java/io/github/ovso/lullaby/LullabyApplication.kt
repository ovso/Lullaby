package io.github.ovso.lullaby

import android.app.Application
import io.github.ovso.lullaby.data.AppContainer
import io.github.ovso.lullaby.data.AppContainerImpl

class LullabyApplication : Application() {
  lateinit var container: AppContainer
  override fun onCreate() {
    super.onCreate()
    container = AppContainerImpl(this)
  }
}
