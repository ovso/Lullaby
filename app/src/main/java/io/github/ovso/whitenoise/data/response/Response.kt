package io.github.ovso.whitenoise.data.response

import kotlinx.serialization.Serializable


@Serializable
data class Response(
    val lullabies: List<LullabySectionResponse>,
)

@Serializable
data class LullabyResponse(
    val id: String,
    val name: String,
)

@Serializable
data class LullabySectionResponse(
    val section: String,
    val items: List<LullabyResponse>,
)