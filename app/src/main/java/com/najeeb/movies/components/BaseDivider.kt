package com.najeeb.movies.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.najeeb.movies.ui.theme.toColor

@Composable
fun BaseDivider(){
  Divider(
    color = "#EEEEEE".toColor(),
    modifier = Modifier
      .fillMaxWidth()
      .height(1.dp)
  )
}