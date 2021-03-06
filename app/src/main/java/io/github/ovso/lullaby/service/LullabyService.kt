package io.github.ovso.lullaby.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import androidx.core.app.NotificationCompat
import io.github.ovso.lullaby.R
import io.github.ovso.lullaby.player.LullabyPlayer
import io.github.ovso.lullaby.player.LullabyPlayerImpl
import io.github.ovso.lullaby.utils.ARGS

const val NOTIFICATION_ID = 10
const val CHANNEL_ID = "primary_notification_channel"

class LullabyService : Service() {
  private val mediaPlayer:LullabyPlayer by lazy { LullabyPlayerImpl(applicationContext) }

  override fun onCreate() {
    super.onCreate()
    createNotificationChannel()
    NotificationCompat.Builder(this, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_notification)
      .build().also { startForeground(NOTIFICATION_ID, it) }
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    val url = intent?.getStringExtra(ARGS) ?: mediaPlayer.getDefaultUrl()
    mediaPlayer.play(url)
    return super.onStartCommand(intent, flags, startId)
  }

  override fun onBind(intent: Intent?): IBinder? {
    return null
  }

  override fun onDestroy() {
    super.onDestroy()
    mediaPlayer.stop()
  }

  private fun createNotificationChannel() {
    val notificationChannel = NotificationChannel(
      CHANNEL_ID,
      "Lullaby Channel",
      NotificationManager.IMPORTANCE_LOW
    ).apply {
      enableLights(true)
      lightColor = Color.RED
    }

    val notificationManager = applicationContext.getSystemService(
      Context.NOTIFICATION_SERVICE
    ) as NotificationManager
    notificationManager.createNotificationChannel(
      notificationChannel
    )
  }

  override fun onTaskRemoved(rootIntent: Intent?) {
    super.onTaskRemoved(rootIntent)
    mediaPlayer.stop()
    this.stopSelf()
  }
}
