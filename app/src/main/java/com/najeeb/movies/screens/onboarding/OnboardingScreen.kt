package com.najeeb.movies.screens.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.najeeb.movies.R
import com.najeeb.movies.components.GradientButton
import com.najeeb.movies.components.IsHaveAccount
import com.najeeb.movies.core.HelperSize.defaultPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
  onGetStartedClick: () -> Unit,
  onLoginClick: () -> Unit
) {
  val systemUiController = rememberSystemUiController()

  SideEffect {
    systemUiController.setStatusBarColor(
      color = Color.Transparent,
      darkIcons = true
    )
  }

  Scaffold(
    modifier = Modifier.background(MaterialTheme.colorScheme.background),
    containerColor = MaterialTheme.colorScheme.background
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      OnboardingImageSection()

      OnboardingTextSection(
        onGetStartedClick = onGetStartedClick,
        onLoginClick = onLoginClick
      )
    }
  }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun OnboardingImageSection() {
  Box(
//    modifier = Modifier.size(350.dp),
    contentAlignment = Alignment.BottomCenter
  ) {

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {

      Image(
        painter = painterResource(id = R.drawable.onboarding_background),
        contentDescription = null,
        modifier = Modifier
          .fillMaxWidth()
          .width(maxWidth)
          .height(maxHeight / 2),
      )
    }
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .offset(y = (-10).dp),
      contentAlignment = Alignment.BottomCenter
    ) {
      Image(
        painter = painterResource(id = R.drawable.onboarding_man),
        contentDescription = stringResource(R.string.onboarding_title),
        alignment = Alignment.BottomCenter,
        modifier = Modifier
          .size(300.93.dp)
          .aspectRatio(12f / 16f)
      )
    }
  }
}

@Composable
private fun OnboardingTextSection(
  onGetStartedClick: () -> Unit,
  onLoginClick: () -> Unit
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .padding(1.dp)
      .offset(y = (-40).dp),
    verticalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = stringResource(id = R.string.onboarding_title),
      style = MaterialTheme.typography.headlineSmall.copy(
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold
      ),
      textAlign = TextAlign.Center
    )

    Spacer(Modifier.height(10.dp))

    GradientButton(
      text = stringResource(R.string.get_started),
      onClick = onGetStartedClick,
    )

    Spacer(Modifier.height(defaultPadding.dp))

    IsHaveAccount(
      isSignIn = false,
      onClick = onLoginClick
    )

    Spacer(Modifier.height(defaultPadding.dp * 2))
  }
}