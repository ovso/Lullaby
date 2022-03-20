package io.github.ovso.lullaby.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.lullaby.exts.showOpeningAds
import io.github.ovso.lullaby.service.LullabyService

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    showOpeningAds(object : FullScreenContentCallback() {
      override fun onAdFailedToShowFullScreenContent(p0: AdError) {
        super.onAdFailedToShowFullScreenContent(p0)
        showContent()
      }

      override fun onAdDismissedFullScreenContent() {
        super.onAdDismissedFullScreenContent()
        showContent()
      }

    })
  }

  private fun showContent() {
    setContent {
      LullabyApp(context = this@MainActivity)
    }
  }

  override fun onBackPressed() {
    super.onBackPressed()
    applicationContext.stopService(Intent(this, LullabyService::class.java))
  }
}
