package io.github.ovso.data.lullaby

import io.github.ovso.domain.LullabyItem
import io.github.ovso.domain.LullabySection
import javax.inject.Inject

class LullabyMapper @Inject constructor() : EntityMapper<LullabySectionResponse, LullabySection> {
  override fun mapFromEntity(entity: LullabySectionResponse): LullabySection =
    LullabySection(
      section = entity.section,
      items = entity.items.map {
        LullabyItem(
          name = it.name,
          id = it.id
        )
      }
    )

  override fun mapToEntity(domainModel: LullabySection) =
    throw UnsupportedOperationException("")

  fun mapFromList(entities: List<LullabySectionResponse>): List<LullabySection> =
    entities.map {
      mapFromEntity(it)
    }
}
