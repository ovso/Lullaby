/*
 * Copyright 2022 Google LLC
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
package io.github.ovso.lullaby

import android.app.Application
import io.github.ovso.lullaby.data.AppContainer
import io.github.ovso.lullaby.data.AppContainerImpl
import io.github.ovso.lullaby.player.LullabyPlayer
import io.github.ovso.lullaby.player.LullabyPlayerImpl

class LullabyApplication : Application() {
    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer
    lateinit var player: LullabyPlayer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
        player = LullabyPlayerImpl(this)
    }
}
