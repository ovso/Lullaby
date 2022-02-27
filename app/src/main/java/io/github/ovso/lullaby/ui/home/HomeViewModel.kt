package io.github.ovso.lullaby.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ovso.domain.LullabyEntity
import io.github.ovso.domain.usecase.LullabyUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LullabiesUiState(
  val lullabies: List<LullabyEntity> = emptyList(),
  val loading: Boolean = false,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val useCase: LullabyUseCase
) : ViewModel() {

  private val _uiState = MutableStateFlow(LullabiesUiState(loading = true))
  val uiState: StateFlow<LullabiesUiState> = _uiState.asStateFlow()

  val selectedLullaby: StateFlow<Set<LullabyEntity>> =
    useCase.observeSelected().stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = emptySet()
    )

  init {
    refreshAll()
  }

  fun toggleSelection(model: LullabyEntity) {
    viewModelScope.launch {
      useCase.toggleSelection(model)
    }
  }

  private fun refreshAll() {
    _uiState.update { it.copy(loading = true) }

    viewModelScope.launch {
      // Trigger repository requests in parallel
      val lullabiesDeferred = async { useCase.getLullabies() }

      // Wait for all requests to finish
      val lullabies = lullabiesDeferred.await()
      _uiState.update {
        it.copy(
          loading = false,
          lullabies = lullabies,
        )
      }
    }
  }

}
