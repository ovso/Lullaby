package io.github.ovso.domain

import kotlinx.serialization.Serializable

@Serializable
data class LullabyEntity(
  val title:String,
  val resName:String,
  val author:String,
)
