package io.github.ovso.domain.repository

import io.github.ovso.domain.Lullaby
import io.github.ovso.domain.Result
import kotlinx.coroutines.flow.Flow

interface LullabyRepository {
  suspend fun getLullabies(): Result<List<Lullaby>>
  suspend fun toggleSelection(item: Lullaby)
  fun observeSelected(): Flow<Set<Lullaby>>
}
