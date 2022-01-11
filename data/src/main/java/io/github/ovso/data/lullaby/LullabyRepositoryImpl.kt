package io.github.ovso.data.lullaby

import io.github.ovso.domain.LullabyItem
import io.github.ovso.domain.LullabySection
import io.github.ovso.domain.repository.LullabyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LullabyRepositoryImpl(
  private val mapper: LullabyMapper,
  private val resProvider: ResProvider,
) : LullabyRepository {
  override suspend fun getLullabies(): List<LullabySection> {
    return withContext(Dispatchers.Default) {

      emptyList()
    }
  }

  override suspend fun toggleSelection(model: LullabyItem) {
    TODO("Not yet implemented")
  }

  override fun observeSelected(): Set<LullabyItem> {
    TODO("Not yet implemented")
  }
}
