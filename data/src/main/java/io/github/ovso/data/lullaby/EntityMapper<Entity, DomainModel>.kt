package io.github.ovso.data.lullaby

interface EntityMapper<Entity, DomainModel> {
  fun toDomainModel(entity: Entity): DomainModel
}
