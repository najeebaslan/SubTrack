package com.najeeb.movies
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.najeeb.movies.screens.connect_wallet.ConnectWalletScreen
import com.najeeb.movies.screens.wallet.WalletScreen
import com.najeeb.movies.ui.theme.MoviesTheme


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MoviesTheme {
        MoviesApp()
      }
    }
  }
}

