package io.github.ovso.lullaby

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp
import io.github.ovso.lullaby.data.AppContainer
import io.github.ovso.lullaby.data.AppContainerImpl

@HiltAndroidApp
class LullabyApplication : Application() {
  lateinit var container: AppContainer
  override fun onCreate() {
    super.onCreate()
    container = AppContainerImpl(this)
    MobileAds.initialize(this) {}
  }
}
