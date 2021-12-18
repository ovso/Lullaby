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

package io.github.ovso.whitenoise.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.ovso.whitenoise.ui.home.InterestsViewModel
import com.example.jetnews.ui.theme.LullabyTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.github.ovso.whitenoise.data.AppContainer
import io.github.ovso.whitenoise.ui.home.InterestsScreen
import io.github.ovso.whitenoise.ui.home.rememberHomeContent

@Composable
fun LullabyApp(
    appContainer: AppContainer,
) {
    LullabyTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            val darkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
            }

            val interestsViewModel: InterestsViewModel = viewModel(
                factory = InterestsViewModel.provideFactory(appContainer.lullabyRepository)
            )

            val tabContent = rememberHomeContent(interestsViewModel)

            val scaffoldState = rememberScaffoldState()

            InterestsScreen(
                tabContent = tabContent,
                scaffoldState = scaffoldState
            )
        }
    }
}