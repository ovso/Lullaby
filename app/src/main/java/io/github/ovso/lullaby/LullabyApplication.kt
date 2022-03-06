package io.github.ovso.lullaby

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp
import io.github.ovso.winfo.DEBUG

@HiltAndroidApp
class LullabyApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    if (DEBUG) {
      Logger.addLogAdapter(AndroidLogAdapter())
    }

    MobileAds.initialize(this) {}
  }
}
