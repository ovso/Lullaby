package io.github.ovso.lullaby.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.lullaby.exts.showInterstitialAd
import io.github.ovso.lullaby.service.LullabyService

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    showOpeningAds()
    showInterstitialAd({
      Logger.d("ad error: $it")
    }) {
      it.show(this)
    }
    WindowCompat.setDecorFitsSystemWindows(window, false)
    setContent {
      LullabyApp(context = this)
    }
  }

  override fun onBackPressed() {
    super.onBackPressed()
    applicationContext.stopService(Intent(this, LullabyService::class.java))
  }
}
