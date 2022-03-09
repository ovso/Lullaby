package io.github.ovso.lullaby.data

import io.github.ovso.domain.Lullaby

fun LullabyModel.toLullaby(): Lullaby {
  return Lullaby(
    title = this.title,
    resName = this.resName,
    author = this.author,
  )
}

fun Lullaby.toLullabyModel(): LullabyModel {
  return LullabyModel(
    title = this.title,
    resName = this.resName,
    author = this.author,
  )
}
