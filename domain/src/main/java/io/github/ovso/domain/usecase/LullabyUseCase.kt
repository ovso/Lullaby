package io.github.ovso.domain.usecase

import io.github.ovso.domain.LullabyEntity
import io.github.ovso.domain.repository.LullabyRepository
import kotlinx.coroutines.flow.Flow

class LullabyUseCase(
  private val repository: LullabyRepository
) {
  suspend fun getLullabies(): List<LullabyEntity> {
    return repository.getLullabies()
  }

  suspend fun toggleSelection(model: LullabyEntity) {
    repository.toggleSelection(model)
  }

  fun observeSelected(): Flow<Set<LullabyEntity>> {
    return repository.observeSelected()
  }

}
