package io.github.ovso.data.lullaby

import kotlinx.serialization.Serializable

@Serializable
data class LullabyItemResponse(
  val id: String,
  val name: String,
)
