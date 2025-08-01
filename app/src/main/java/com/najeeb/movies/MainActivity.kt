package com.najeeb.movies

import android.R.attr.delay
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
//import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.najeeb.movies.components.SplashScreen
import com.najeeb.movies.navigation.EmailCard
import com.najeeb.movies.navigation.mockEmails
import com.najeeb.movies.onboarding.OnboardingScreen
import com.najeeb.movies.ui.theme.MoviesTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    // Handle splash screen
     installSplashScreen().setKeepOnScreenCondition { false }

    super.onCreate(savedInstanceState)

//    enableEdgeToEdge()

    setContent {
      MoviesTheme {
        OnboardingScreen(
          onGetStartedClick = { /* TODO: Navigate to main app */ },
          onLoginClick = { /* TODO: Navigate to login */ }
        )
      }
//        // State to control splash screen visibility
//        var showSplash by remember { mutableStateOf(true) }
//
//        LaunchedEffect(Unit) {
//
//          delay(1000) // Show splash for 1 second
//          showSplash = false
//        }
//
//        if (showSplash) {
//
//          OnboardingScreen()
////          SplashScreen()
//        } else {
//          MoviesApp()
//        }
//      }
    }

  }
}


@Composable
fun InboxScreen() {

  LazyColumn(modifier = Modifier.fillMaxSize()) {
    items(mockEmails) { email ->
      EmailCard(email = email,
        isSelected = email.isStarred,
        onClick = { /* Handle email click */ },
        onLongClick = { /* Handle email long click */ }
      )
    }
  }
}

@Composable
fun SentScreen() {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text("Sent Emails", style = MaterialTheme.typography.headlineSmall)
  }
}

@Composable
fun TrashScreen() {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text("Trash", style = MaterialTheme.typography.headlineSmall)
  }
}
