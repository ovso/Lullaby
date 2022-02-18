package io.github.ovso.lullaby

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LullabyApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    MobileAds.initialize(this) {}
    Logger.addLogAdapter(AndroidLogAdapter())
  }
}
