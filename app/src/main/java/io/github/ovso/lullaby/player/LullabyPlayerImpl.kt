package io.github.ovso.lullaby.player

import android.content.Context
import android.media.MediaPlayer
import android.os.PowerManager

interface LullabyPlayer {
  fun play(resName: String)
  fun stop()
  fun getDefaultResName(): String = "white_noise_10min_64kbps"
}

class LullabyPlayerImpl(
  private val context: Context,
) : LullabyPlayer {
  private var player: MediaPlayer? = null
  override fun play(resName: String) {
    val resId = context.resources.getIdentifier(resName, "raw", context.packageName)
    player = MediaPlayer.create(context, resId).apply {
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
