package io.github.ovso.data.lullaby

import io.github.ovso.domain.Lullaby
import kotlinx.serialization.Serializable

@Serializable
data class Response(
  val items: List<Lullaby>,
)

@Serializable
data class LullabyItemResponse(
  val id: String,
  val name: String,
)

@Serializable
data class LullabySectionResponse(
  val section: String?,
  val items: List<LullabyItemResponse>,
)
