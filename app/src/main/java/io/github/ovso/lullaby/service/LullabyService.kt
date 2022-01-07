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
    private var mediaPlayer: MediaPlayer? = null

    private class AudioServiceBinder : Binder() {

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
            setOnSeekCompleteListener {

            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.let {
            it.stop()
            it.release()
        }
        mediaPlayer = null
        mediaPlayer.takeIf {
            it != null
        } ?: {}
    }
}