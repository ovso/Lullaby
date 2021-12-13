package io.github.ovso.whitenoise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import io.github.ovso.whitenoise.ui.home.Home
import io.github.ovso.whitenoise.ui.theme.WhiteNoiseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhiteNoiseTheme {
                Surface {
                    Home()
                }
            }
        }
    }
}