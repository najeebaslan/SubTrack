package com.najeeb.movies.onboarding

import androidx.compose.ui.res.stringResource
import com.najeeb.movies.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.najeeb.movies.HelperSize.defaultPadding
import com.najeeb.movies.components.GradientButton
import com.najeeb.movies.components.IsHaveAccount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
  onGetStartedClick: () -> Unit,
  onLoginClick: () -> Unit
) {

  Scaffold(
    modifier = Modifier.background(MaterialTheme.colorScheme.background),
  ) { padding ->

    Column(
      modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .padding(bottom = defaultPadding.dp),

      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {

      Box(
        modifier = Modifier.size(350.dp),
        contentAlignment = Alignment.BottomCenter
      ) {

        Image(
          painter = painterResource(id = R.drawable.onboarding_background),
          contentDescription = null,
//          contentScale = ContentScale.FillBounds,
          modifier = Modifier.size(400.dp).padding(0.dp)
        )

        Box(
          modifier = Modifier
            .fillMaxWidth().offset(y = (10).dp),
          contentAlignment = Alignment.BottomCenter
        ) {
          Image(
            painter = painterResource(id = R.drawable.onboarding_man),
            contentDescription = "App Logo",
            alignment = Alignment.BottomCenter,
            modifier = Modifier.size(300.93.dp)
          )
        }
      }

      Text(
        text = stringResource(id = R.string.onboarding_title),
        style = MaterialTheme.typography.headlineSmall.copy(
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.Bold
        ),
        textAlign = TextAlign.Center
      )
      GradientButton(
        text = "Get Started",
        onClick = onGetStartedClick,
      )
      IsHaveAccount(
        isSignIn = false,
        onNavigate = { onLoginClick }
      )

    }
  }
}