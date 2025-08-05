package com.najeeb.movies.screens.connect_wallet.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.najeeb.movies.components.ToggleButton
import com.najeeb.movies.ui.theme.ActiveTextToggleButtonColor
import com.najeeb.movies.ui.theme.BackgroundCardColor
import kotlinx.coroutines.launch

@Composable
fun BodyConnectWallet() {
  val pagerState = rememberPagerState(
    initialPage = 0,
    pageCount = { 2 }
  )
  val coroutineScope = rememberCoroutineScope()

  Column(
    Modifier.padding(horizontal = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {

    Spacer(Modifier.height(30.dp))

    ToggleButton(
      selectedIndex = pagerState.currentPage,
      onToggle = { page ->
        coroutineScope.launch {
          pagerState.animateScrollToPage(page)
        }
      },
      height = 55.dp,
      leftDescription = "Cards",
      rightDescription = "Accounts",
      toggleColor = MaterialTheme.colorScheme.background,
      toggleBackgroundColor = BackgroundCardColor,
      toggleBorderColor = Color.Transparent,
      activeTextColor = ActiveTextToggleButtonColor,
      inactiveTextColor = ActiveTextToggleButtonColor,
    )

    Spacer(Modifier.height(16.dp))

    HorizontalPager(state = pagerState) { page ->
      when (page) {
        0 -> CardPage(150)
        1 -> AccountsPage()
      }
    }
  }
}