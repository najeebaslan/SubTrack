package com.najeeb.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.najeeb.movies.core.SettingsStore
import com.najeeb.movies.screens.onboarding.OnboardingScreen
import com.najeeb.movies.ui.theme.MoviesTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      MoviesTheme {
        val onboardingCompletedFlow = SettingsStore.isOnboardingCompleted(this)
        val onboardingCompleted by onboardingCompletedFlow.collectAsState(initial = null)

        when (onboardingCompleted) {
          null -> {
            // while loading, you can show a placeholder, splash, or nothing
          }

          false -> {
            OnboardingScreen(
              onGetStartedClick = {
                lifecycleScope.launch {
                  SettingsStore.setOnboardingCompleted(this@MainActivity, true)
                }
              },
              onLoginClick = {
                lifecycleScope.launch {
                  SettingsStore.setOnboardingCompleted(this@MainActivity, true)
                }
              }
            )
          }

          true -> MoviesApp()

        }
      }
    }
  }
}
