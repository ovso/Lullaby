package io.github.ovso.lullaby.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import io.github.ovso.lullaby.player.LullabyPlayerImpl

class LullabyService : Service() {
    private val binder = AudioServiceBinder()

    private val mediaPlayer by lazy { LullabyPlayerImpl(applicationContext) }

    private class AudioServiceBinder : Binder() {

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getStringExtra("key")?.let {
            mediaPlayer.play()
        } ?: mediaPlayer.stop()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}