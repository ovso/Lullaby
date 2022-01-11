package io.github.ovso.data.lullaby

import io.github.ovso.domain.LullabyItem
import io.github.ovso.domain.LullabySection
import io.github.ovso.domain.repository.LullabyRepository

class LullabyRepositoryImpl (
  private val mapper: LullabyMapper
) : LullabyRepository {
  override suspend fun getLullabies(): List<LullabySection> {
    TODO("Not yet implemented")
  }

  override suspend fun toggleSelection(model: LullabyItem) {
    TODO("Not yet implemented")
  }

  override fun observeSelected(): Set<LullabyItem> {
    TODO("Not yet implemented")
  }
}
