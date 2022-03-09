package io.github.ovso.domain.usecase

import io.github.ovso.domain.Lullaby
import io.github.ovso.domain.repository.LullabyRepository
import kotlinx.coroutines.flow.Flow

class LullabyUseCase(
  private val repository: LullabyRepository
) {
  suspend fun getLullabies(): List<Lullaby> {
    return repository.getLullabies()
  }

  suspend fun toggleSelection(model: Lullaby) {
    repository.toggleSelection(model)
  }

  fun observeSelected(): Flow<Set<Lullaby>> {
    return repository.observeSelected()
  }

}
