package io.github.ovso.lullaby.player

import android.content.Context
import android.media.MediaPlayer
import android.os.PowerManager
import androidx.core.net.toUri

interface LullabyPlayer {
  fun play(resName: String)
  fun stop()
  fun getDefaultResName(): String = "white_noise_10min_64kbps"
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
