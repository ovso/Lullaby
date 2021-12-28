/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ovso.lullaby.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.github.ovso.lullaby.data.LullabyModel
import io.github.ovso.lullaby.data.lullaby.LullabyRepository
import io.github.ovso.lullaby.data.lullaby.LullabySectionModel
import io.github.ovso.lullaby.data.successOr
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class LullabiesUiState(
    val lullabies: List<LullabySectionModel> = emptyList(),
    val loading: Boolean = false,
)

class HomeViewModel(
    private val lullabyRepository: LullabyRepository
) : ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(LullabiesUiState(loading = true))
    val uiState: StateFlow<LullabiesUiState> = _uiState.asStateFlow()

    private val _play = MutableStateFlow(false)
    val play:StateFlow<Boolean> = _play

    val selectedLullaby =
        lullabyRepository.observeSelected().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )


    init {
        refreshAll()
    }

    fun toggleSelection(model: LullabyModel) {
        viewModelScope.launch {
            lullabyRepository.toggleSelection(model)
            _play.emit(_play.value.not())
        }
    }

    private fun refreshAll() {
        _uiState.update { it.copy(loading = true) }

        viewModelScope.launch {
            // Trigger repository requests in parallel
            val lullabiesDeferred = async { lullabyRepository.getLullabies() }

            // Wait for all requests to finish
            val lullabies = lullabiesDeferred.await().successOr(emptyList())
            _uiState.update {
                it.copy(
                    loading = false,
                    lullabies = lullabies,
                )
            }
        }
    }

    /**
     * Factory for InterestsViewModel that takes PostsRepository as a dependency
     */
    companion object {
        fun provideFactory(
            lullabyRepository: LullabyRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(lullabyRepository) as T
            }
        }
    }
}
