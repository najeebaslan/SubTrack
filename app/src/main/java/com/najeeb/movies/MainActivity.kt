package com.najeeb.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.najeeb.movies.screens.home.HomeScreen
import com.najeeb.movies.screens.onboarding.OnboardingScreen
import com.najeeb.movies.ui.theme.MoviesTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
//    WindowCompat.setDecorFitsSystemWindows(window, false)
//    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

    setContent {
      MoviesTheme {
//        HomeScreen()
//        OnboardingScreen(
//          onGetStartedClick={},
//          onLoginClick={}
//
//        )
        MoviesApp()
      }
    }
  }
}