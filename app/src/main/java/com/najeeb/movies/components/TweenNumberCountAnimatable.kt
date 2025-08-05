package com.najeeb.movies.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.najeeb.movies.screens.wallet.WalletViewModel

@Composable
fun TweenCurrencyCountAnimatableOptimized(
  targetAmount: Double,
  viewModel: WalletViewModel = viewModel(),
  durationMillis: Int = 800,
  currencySymbol: String = "$",
  modifier: Modifier = Modifier,
  textStyle: TextStyle = MaterialTheme.typography.headlineLarge
) {
  val startSaved by viewModel.displayAmount.collectAsState()
  val alreadyAnimated by viewModel.animated.collectAsState()
  val formatter = remember {
    java.text.NumberFormat.getNumberInstance(java.util.Locale.getDefault()).apply {
      minimumFractionDigits = 2
      maximumFractionDigits = 2
      isGroupingUsed = true
    }
  }

  val anim = remember { Animatable(startSaved) }

  // update VM continuously while animating so the value is preserved
  LaunchedEffect(anim) {
    snapshotFlow { anim.value }
      .collect { viewModel.updateDisplay(it) }
  }

  LaunchedEffect(targetAmount) {
    // if we already reached target and flagged as animated -> no animation
    if (alreadyAnimated && anim.value == targetAmount.toFloat()) return@LaunchedEffect

    // if anim was restored to some saved value, animate from that to target
    anim.snapTo(startSaved)
    anim.animateTo(
      targetValue = targetAmount.toFloat(),
      animationSpec = tween(durationMillis)
    )
    viewModel.setAnimatedDone(true)
  }

  val displayText by remember(anim.value) {
    derivedStateOf {
      "$currencySymbol ${formatter.format(anim.value.toDouble())}"
    }
  }

  Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
    Text(text = displayText, style = textStyle)
  }
}
