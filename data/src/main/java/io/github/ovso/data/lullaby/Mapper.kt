package io.github.ovso.data.lullaby

import io.github.ovso.domain.Lullaby

fun LullabyResponse.toLullaby(): Lullaby {
  return Lullaby(
    title = this.title,
    resName = this.resName,
    author = this.author
  )
}
