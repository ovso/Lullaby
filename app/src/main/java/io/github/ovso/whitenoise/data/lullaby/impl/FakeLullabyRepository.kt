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

package io.github.ovso.whitenoise.data.lullaby.impl

import android.content.Context
import io.github.ovso.whitenoise.R
import io.github.ovso.whitenoise.data.LullabyModel
import io.github.ovso.whitenoise.data.Result
import io.github.ovso.whitenoise.data.lullaby.LullabyRepository
import io.github.ovso.whitenoise.data.lullaby.LullabySection
import io.github.ovso.whitenoise.data.lullaby.Selection
import io.github.ovso.whitenoise.utils.addOrRemove
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Implementation of InterestRepository that returns a hardcoded list of
 * topics, people and publications synchronously.
 */
class FakeLullabyRepository(private val context: Context) : LullabyRepository {

    private val lullabies by lazy {
        listOf(
            LullabySection(
                title = "자장가",
                models = listOf("브람스 자장가", "모짜르트 자장가", "바흐 자장가").map {
                    LullabyModel(name = it, id = R.raw.white_noise_10m)
                }
            ),
            LullabySection(
                title = "자연의 소리",
                models = listOf("파도소리", "냇물 소리", "빗소리", "천둥소리", "바람소리").map {
                    LullabyModel(name = it, id = R.raw.white_noise_10m)
                }
            ),
            LullabySection(
                title = "도시",
                models = listOf("카페", "자동차", "라디오").map {
                    LullabyModel(name = it, id = R.raw.white_noise_10m)
                }
            ),
            LullabySection(
                title = "Hum",
                models = listOf("Hum1", "Hum2").map {
                    LullabyModel(name = it, id = R.raw.white_noise_10m)
                }
            )
        )
    }

    // for now, keep the selections in memory
    private val selected = MutableStateFlow(setOf<Selection>())

    // Used to make suspend functions that read and update state safe to call from any thread
    private val mutex = Mutex()

    override suspend fun getLullabies(): Result<List<LullabySection>> {
        return Result.Success(lullabies)
    }

    override suspend fun toggleSelection(selection: Selection) {
        mutex.withLock {
            val set = selected.value.toMutableSet()
            set.addOrRemove(selection)
            selected.value = set
        }
    }

    override fun observeSelected(): Flow<Set<Selection>> = selected
}
