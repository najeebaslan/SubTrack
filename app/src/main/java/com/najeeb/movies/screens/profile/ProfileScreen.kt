package com.najeeb.movies.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.najeeb.movies.R
import com.najeeb.movies.components.CustomAppTopBar
import com.najeeb.movies.components.HeaderBackground
import com.najeeb.movies.screens.home.NotificationIcon


@Composable
fun ProfileScreen(
  navController: NavHostController,
) {
  val systemUiController = rememberSystemUiController()
  SideEffect {
    systemUiController.setStatusBarColor(
      color = Color.Transparent,
      darkIcons = false
    )
  }

  Scaffold(
    topBar = {
      CustomAppTopBar(
        title = "Profile",
        containerColor = Color.Transparent,
        titleColor = Color.White,
        navigationIconColor = Color.White,
        onBackClick = { navController.popBackStack() },
        actions = {
          Box(
            modifier = Modifier
              .padding(horizontal = 16.dp)
          ) {
            NotificationIcon(
              onClick = {}
            )
          }
        }
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .padding()
        .verticalScroll(
          rememberScrollState()
        ),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Top
    ) {
      Box(
        modifier = Modifier
          .padding()
          .height(220.dp),
        contentAlignment = Alignment.TopCenter
      ) {
        HeaderBackground()

      }
//      FromAddExpense(
//        nameOnClick = {},
//        modifier = Modifier
//          .offset(y = (-60).dp *2)
//      )
    }
  }
}
