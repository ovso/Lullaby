package io.github.ovso.lullaby

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp
import io.github.ovso.winfo.DEBUG

@HiltAndroidApp
class LullabyApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    if (DEBUG) {
      val testDeviceIds = listOf("1D30C2DF60EE05B068D4D17AC4072D7C")
      val configuration = RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
      MobileAds.setRequestConfiguration(configuration)

      Logger.addLogAdapter(AndroidLogAdapter())
    }

    MobileAds.initialize(this) {}
  }
}
