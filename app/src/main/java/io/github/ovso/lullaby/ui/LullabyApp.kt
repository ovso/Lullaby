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

package io.github.ovso.lullaby.ui

import android.content.Context
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetnews.ui.theme.LullabyTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.github.ovso.lullaby.data.AppContainer
import io.github.ovso.lullaby.player.LullabyPlayer
import io.github.ovso.lullaby.player.LullabyPlayerImpl
import io.github.ovso.lullaby.ui.home.HomeScreen
import io.github.ovso.lullaby.ui.home.HomeViewModel
import io.github.ovso.lullaby.ui.home.rememberHomeContent

@Composable
fun LullabyApp(
    appContainer: AppContainer,
    context: Context,
) {
    LullabyTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            val darkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
            }

            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(
                    appContainer.lullabyRepository
                )
            )

            val player: LullabyPlayer = remember { LullabyPlayerImpl(context) }

            val content = rememberHomeContent(homeViewModel)
            HomeScreen(
                content = content,
            )

            val selectedLullabies by homeViewModel.selectedLullaby.collectAsState()
            player.stop()
            if (selectedLullabies.isNotEmpty()) {
                player.play()
            } else {
                player.stop()
            }

        }
    }
}
