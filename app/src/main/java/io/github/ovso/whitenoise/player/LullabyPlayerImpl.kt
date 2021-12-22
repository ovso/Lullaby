package io.github.ovso.whitenoise.player

import android.content.Context
import android.media.MediaPlayer
import io.github.ovso.whitenoise.R


interface LullabyPlayer {
    fun play()
    fun stop()
}

class LullabyPlayerImpl(
    private val context: Context,
) : LullabyPlayer {
    private var player: MediaPlayer? = null
    override fun play() {
        player = MediaPlayer.create(context, R.raw.white_noise_10m)
        player?.start()
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}