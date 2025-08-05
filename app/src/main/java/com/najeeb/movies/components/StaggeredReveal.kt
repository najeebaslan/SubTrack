package com.najeeb.movies.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StaggeredReveal(
  index: Int,
  baseDelay: Long = 80L,
  visibleKey: Any, // when this changes, animation will replay
  enterDuration: Int = 420,
  content: @Composable () -> Unit
) {
  // control visibility per item — we drive it with LaunchedEffect so we can stagger
  var visible by remember { mutableStateOf(false) }

  // replay when visibleKey changes
  LaunchedEffect(visibleKey) {
    visible = false
    // small offset so parent recomposition settles
    delay(30)
    delay(index * baseDelay)
    visible = true
  }

  AnimatedVisibility(
    visible = visible,
    enter = slideInVertically(
      // start below its height (it -> its height)
      initialOffsetY = { it },
      animationSpec = tween(durationMillis = enterDuration)
    ) + fadeIn(animationSpec = tween(enterDuration)),
    exit = fadeOut(animationSpec = tween(200))
  ) {
    content()
  }
}
