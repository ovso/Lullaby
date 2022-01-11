package io.github.ovso.data.lullaby

data class LullabySectionResponse(
  val section:String?,
  val items: List<LullabyItemResponse>,
)
