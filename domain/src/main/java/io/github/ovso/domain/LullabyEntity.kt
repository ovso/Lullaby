package io.github.ovso.domain

import kotlinx.serialization.Serializable

@Serializable
data class Lullaby(
  val title:String,
  val url:String,
  val author:String,
)
