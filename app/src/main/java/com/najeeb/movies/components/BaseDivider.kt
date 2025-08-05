package com.najeeb.movies.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.najeeb.movies.ui.theme.GrayColor
import com.najeeb.movies.ui.theme.toColor

@Composable
fun BaseDivider(){
  Divider(
    color =if(isSystemInDarkTheme()) GrayColor else  "#DDDDDD".toColor(),
    modifier = Modifier
      .fillMaxWidth()
      .height(0.5.dp)
  )
}