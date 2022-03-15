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
  override fun play(resName: String) {
    val resId = context.resources.getIdentifier(resName, "raw", context.packageName)
    val uri = "https://blogattach.naver.com/25b0398a9cc7c11d31d3b185bd5f205df6ad53b355/20220312_54_blogfile/ovso_1647050297751_RuP1kE_mp3/ambient_winter_and_christmas_piano_lullaby.mp3".toUri()
    player = MediaPlayer.create(context, uri).apply {
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
