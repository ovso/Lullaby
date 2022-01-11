package io.github.ovso.domain.repository

import io.github.ovso.domain.LullabyItem
import io.github.ovso.domain.LullabySection

interface LullabyRepository {
  suspend fun getLullabies(): List<LullabySection>
  suspend fun toggleSelection(model: LullabyItem)
  fun observeSelected(): Set<LullabyItem>
}
