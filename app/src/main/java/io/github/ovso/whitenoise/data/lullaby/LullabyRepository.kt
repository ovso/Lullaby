/*
 * Copyright 2020 The Android Open Source Project
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

package io.github.ovso.whitenoise.data.lullaby

import io.github.ovso.whitenoise.data.Result
import kotlinx.coroutines.flow.Flow

data class LullabySection(val title: String, val names: List<String>)

/**
 * Interface to the Interests data layer.
 */
interface LullabyRepository {

    /**
     * Get relevant topics to the user.
     */
    suspend fun getLullabies(): Result<List<LullabySection>>

    /**
     * Toggle between selected and unselected
     */
    suspend fun toggleSelection(topic: Selection)
    /**
     * Currently selected topics
     */
    fun observeSelected(): Flow<Set<Selection>>

}

data class Selection(val section: String, val topic: String)
