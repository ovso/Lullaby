package io.github.ovso.data.lullaby

interface EntityMapper<Entity, DomainModel> {
  fun mapFromEntity(entity: Entity): DomainModel
  fun mapToEntity(domainModel: DomainModel): Entity
}
