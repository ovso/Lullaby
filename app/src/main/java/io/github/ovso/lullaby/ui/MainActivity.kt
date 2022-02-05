package io.github.ovso.lullaby.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import io.github.ovso.lullaby.LullabyApplication
import io.github.ovso.lullaby.exts.showOpeningAds
import io.github.ovso.lullaby.service.LullabyService

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    showOpeningAds()
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val app = application as LullabyApplication
    setContent {
      LullabyApp(app.container, this)
    }
  }

  override fun onBackPressed() {
    super.onBackPressed()
    applicationContext.stopService(Intent(this, LullabyService::class.java))
  }
}
