package io.github.ovso.whitenoise.data

data class LullabyModel(
    val name: String,
    val lullabyId: Int,
    val id: String = "",
    val selected: Boolean = false
)