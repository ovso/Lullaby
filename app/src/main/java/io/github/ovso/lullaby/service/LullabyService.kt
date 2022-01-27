package io.github.ovso.lullaby.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import androidx.core.app.NotificationCompat
import io.github.ovso.lullaby.player.LullabyPlayerImpl
import io.github.ovso.lullaby.utils.ARGS

const val NOTIFICATION_ID = 10
const val CHANNEL_ID = "primary_notification_channel"

class LullabyService : Service() {
  private val mediaPlayer by lazy { LullabyPlayerImpl(applicationContext) }

  override fun onCreate() {
    super.onCreate()
    createNotificationChannel()
    NotificationCompat.Builder(this, CHANNEL_ID)
      .setContentTitle("Lullaby")
      .setContentText("자장가가 재생중입니다.")
      .build().also { startForeground(NOTIFICATION_ID, it) }
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    val resName = intent?.getStringExtra(ARGS) ?: mediaPlayer.getDefaultResName()
    mediaPlayer.play(resName)
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
}
