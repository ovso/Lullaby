package io.github.ovso.lullaby.player

import android.content.Context
import android.media.MediaPlayer
import android.os.PowerManager
import androidx.core.net.toUri

interface LullabyPlayer {
  fun play(url: String)
  fun stop()
  fun getDefaultUrl(): String =
    "https://cdn.pixabay.com/download/audio/2021/09/06/audio_1e760b4ae7.mp3?filename=twinkle-like-a-star-8026.mp3"
}

class LullabyPlayerImpl(
  private val context: Context,
) : LullabyPlayer {
  private var player: MediaPlayer? = null
  override fun play(url: String) {
    player = MediaPlayer.create(context, url.toUri()).apply {
      isLooping = true
      setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
    }
    player?.start()
  }

  override fun stop() {
    player?.stop()
    player?.release()
    player = null
  }
}
