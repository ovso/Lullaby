/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.ovso.lullaby.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import io.github.ovso.lullaby.player.LullabyPlayerImpl
import io.github.ovso.lullaby.utils.ARGS

const val NOTIFICATION_ID = 10
const val CHANNEL_ID = "primary_notification_channel"

class LullabyService : Service() {
  private val binder = AudioServiceBinder()

  private val mediaPlayer by lazy { LullabyPlayerImpl(applicationContext) }

  private class AudioServiceBinder : Binder()

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

  override fun onBind(intent: Intent?): IBinder {
    return binder
  }

  override fun onDestroy() {
    super.onDestroy()
    mediaPlayer.stop()
  }

  private fun createNotificationChannel() {
    val notificationChannel = NotificationChannel(
      CHANNEL_ID,
      "MyApp notification",
      NotificationManager.IMPORTANCE_HIGH
    )
    notificationChannel.enableLights(true)
    notificationChannel.lightColor = Color.RED
    notificationChannel.enableVibration(true)
    notificationChannel.description = "AppApp Tests"

    val notificationManager = applicationContext.getSystemService(
      Context.NOTIFICATION_SERVICE
    ) as NotificationManager
    notificationManager.createNotificationChannel(
      notificationChannel
    )
  }
}
