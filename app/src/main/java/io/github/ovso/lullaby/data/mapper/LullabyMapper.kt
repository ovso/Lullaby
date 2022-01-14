package io.github.ovso.lullaby.data.mapper

import io.github.ovso.lullaby.data.LullabyModel
import io.github.ovso.lullaby.data.response.LullabyResponse

class LullabyMapper : EntityMapper<LullabyResponse, LullabyModel> {
    override fun mapFromEntity(entity: LullabyResponse): LullabyModel =
        LullabyModel(
          title = entity.title,
          resName = entity.resName,
          author = entity.author
        )

    override fun mapToEntity(domainModel: LullabyModel) =
        throw UnsupportedOperationException("")

    fun mapFromList(entities: List<LullabyResponse>): List<LullabyModel> =
        entities.map {
            mapFromEntity(it)
        }
}
