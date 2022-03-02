package io.github.ovso.domain.repository

import io.github.ovso.domain.LullabyEntity
import kotlinx.coroutines.flow.Flow

interface LullabyRepository {
  suspend fun getLullabies(): List<LullabyEntity>
  suspend fun toggleSelection(model: LullabyEntity)
  fun observeSelected(): Flow<Set<LullabyEntity>>
}
