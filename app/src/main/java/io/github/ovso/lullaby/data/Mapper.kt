package io.github.ovso.lullaby.data

import io.github.ovso.domain.LullabyEntity

fun LullabyEntity.toLullabyModel(): LullabyModel {
  return LullabyModel(
    title = this.title,
    resName = this.resName,
    author = this.author,
  )
}

fun LullabyModel.toLullabyEntity(): LullabyEntity {
  return LullabyEntity(
    title = this.title,
    resName = this.resName,
    author = this.author,
  )
}
