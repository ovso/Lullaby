package io.github.ovso.data.lullaby

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val lullabies: List<LullabySectionResponse>,
)

@Serializable
data class LullabyItemResponse(
  val id: String,
  val name: String,
)

@Serializable
data class LullabySectionResponse(
  val section:String?,
  val items: List<LullabyItemResponse>,
)
