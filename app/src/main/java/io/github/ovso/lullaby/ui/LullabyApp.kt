package io.github.ovso.lullaby.ui

import android.content.Context
import android.content.Intent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetnews.ui.theme.LullabyTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.github.ovso.lullaby.data.AppContainer
import io.github.ovso.lullaby.service.LullabyService
import io.github.ovso.lullaby.ui.home.HomeScreen
import io.github.ovso.lullaby.ui.home.HomeViewModel
import io.github.ovso.lullaby.ui.home.rememberHomeContent
import io.github.ovso.lullaby.utils.ARGS

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

      val content = rememberHomeContent(homeViewModel)
      HomeScreen(
        content = content,
      )

      val selectedLullabies by homeViewModel.selectedLullaby.collectAsState()
      if (selectedLullabies.isNotEmpty()) {
        context.also {
          val intent = Intent(context, LullabyService::class.java).apply {
            putExtra(ARGS, selectedLullabies.first().resName)
          }
          it.stopService(intent)
          it.startService(intent)
        }
      } else {
        context.stopService(Intent(context, LullabyService::class.java))
      }
    }
  }
}
