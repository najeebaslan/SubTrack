package com.najeeb.movies.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.najeeb.movies.R

@Composable
fun BaseHeaderBackground(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier,
    contentAlignment = Alignment.TopCenter
  ) {
    Image(
      modifier = Modifier.fillMaxWidth(),
      painter = painterResource(id = R.drawable.home_top_clip),
      contentScale = ContentScale.FillBounds,
      contentDescription = "Header background"
    )

    Image(
      modifier = Modifier.fillMaxWidth(),
      alignment = Alignment.BottomStart,
      painter = painterResource(id = R.drawable.home_top_circles),
      contentDescription = "Decorative circles"
    )
  }
}
