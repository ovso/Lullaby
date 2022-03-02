package io.github.ovso.data.lullaby

import kotlinx.serialization.Serializable

@Serializable
data class LullabyResponse(
  val title:String,
  val resName:String,
  val author:String? = null,
)
