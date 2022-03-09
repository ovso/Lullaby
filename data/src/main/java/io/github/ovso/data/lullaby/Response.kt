package io.github.ovso.data.lullaby

import kotlinx.serialization.Serializable

@Serializable
data class Response(
  val items: List<LullabyEntity>,
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

@Serializable
data class LullabyEntity(
  val title:String,
  val resName:String,
  val author:String,
)
