package io.github.ovso.data.lullaby

import io.github.ovso.domain.Lullaby
import javax.inject.Inject

class LullabyMapper @Inject constructor() : EntityMapper<LullabySectionResponse, Lullaby> {
  override fun mapFromEntity(entity: LullabySectionResponse): Lullaby = Lullaby("", "")

  override fun mapToEntity(domainModel: Lullaby) =
    throw UnsupportedOperationException("")

  fun mapFromList(entities: List<LullabySectionResponse>): List<Lullaby> =
    entities.map {
      mapFromEntity(it)
    }
}
