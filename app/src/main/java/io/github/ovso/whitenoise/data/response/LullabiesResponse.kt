package io.github.ovso.whitenoise.data.response

import kotlinx.serialization.Serializable


@Serializable
data class LullabiesResponse(
    val lullabies: List<Lullaby>,
)

@Serializable
data class Item(
    val id: String,
    val name: String,
)

@Serializable
data class Lullaby(
    val item: List<Item>,
    val section: String,
)