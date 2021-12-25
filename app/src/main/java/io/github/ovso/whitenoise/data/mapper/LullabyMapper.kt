package io.github.ovso.whitenoise.data.mapper

import io.github.ovso.whitenoise.data.LullabyModel
import io.github.ovso.whitenoise.data.lullaby.LullabySectionModel
import io.github.ovso.whitenoise.data.response.LullabySectionResponse

class LullabyMapper : EntityMapper<LullabySectionResponse, LullabySectionModel> {
    override fun mapFromEntity(entity: LullabySectionResponse): LullabySectionModel =
        LullabySectionModel(
            section = entity.section,
            items = entity.items.map {
                LullabyModel(
                    name = it.name,
                    id = it.id
                )
            }
        )


    override fun mapToEntity(domainModel: LullabySectionModel) =
        throw UnsupportedOperationException("")

    fun mapFromList(entities: List<LullabySectionResponse>): List<LullabySectionModel> =
        entities.map {
            mapFromEntity(it)
        }
}