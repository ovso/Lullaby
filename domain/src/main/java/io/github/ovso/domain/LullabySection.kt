package io.github.ovso.domain

data class LullabySection(
  private val section:String?,
  val items: List<LullabyItem>,
)
