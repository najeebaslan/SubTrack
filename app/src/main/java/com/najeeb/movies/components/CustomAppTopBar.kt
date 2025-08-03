package com.najeeb.movies.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.najeeb.movies.ui.theme.IconColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppTopBar(
  title: String,
  onBackClick: () -> Unit = {},
  showBackButton: Boolean = true,
  actions: @Composable () -> Unit = {},
  scrollBehavior: TopAppBarScrollBehavior? = null,
  containerColor: Color = MaterialTheme.colorScheme.background,
  titleColor: Color = IconColor,
  navigationIconColor: Color = IconColor,
) {
  CenterAlignedTopAppBar(
    scrollBehavior =scrollBehavior ,
    title = {
      Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        color = titleColor
      )
    },
    navigationIcon = {
      if (showBackButton) {
        IconButton(onClick = onBackClick) {
          Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "Back",
            tint = navigationIconColor
          )
        }
      }
    },
    actions = {
      actions()
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = containerColor,
      actionIconContentColor = IconColor
    )
  )
}