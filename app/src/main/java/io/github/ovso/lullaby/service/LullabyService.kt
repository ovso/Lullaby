package io.github.ovso.lullaby.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.os.PowerManager

class LullabyService : Service() {
    private var isPrepared: Boolean = false
    private val binder = AudioServiceBinder()
    private lateinit var mediaPlayer: MediaPlayer

    private class AudioServiceBinder : Binder() {
        fun getService(): LullabyService {
            return this
        }
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer().apply {
            setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
            setOnPreparedListener {
                isPrepared = true
                it.start()
            }
            setOnCompletionListener {
                isPrepared = false
            }
            setOnErrorListener { mediaPlayer, what, extra ->
                isPrepared = false
                false
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }
}