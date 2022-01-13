package io.github.ovso.lullaby.data.response

import kotlinx.serialization.Serializable

@Serializable
data class Response(
  val items: List<LullabyResponse>,
)

@Serializable
data class LullabyResponse(
  val resName: String,
  val title: String,
)
