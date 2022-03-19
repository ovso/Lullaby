package io.github.ovso.data.lullaby

import kotlinx.serialization.Serializable

@Serializable
data class Response(
  val items: List<LullabyResponse>,
)

@Serializable
data class LullabyResponse(
  val title:String,
  val url:String?,
  val author:String,
)
