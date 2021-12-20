package io.github.ovso.whitenoise.data

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes


interface LullabyPlayer {
    fun play()
    fun stop()
}

class LullabyPlayerImpl(
    private val context: Context,
    @RawRes private val resId: Int,
) : LullabyPlayer {
    private var player: MediaPlayer? = null
    override fun play() {
        player = MediaPlayer.create(context, resId)
        player?.start()
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}