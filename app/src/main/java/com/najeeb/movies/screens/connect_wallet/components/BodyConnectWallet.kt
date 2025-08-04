package com.najeeb.movies.screens.connect_wallet.components

import com.najeeb.movies.screens.wallet.components.TotalBalanceWithIcon
import com.najeeb.movies.screens.wallet.components.UpcomingBillsListItems


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.najeeb.movies.components.ToggleButton
import com.najeeb.movies.data.TransactionsData
import com.najeeb.movies.data.UpcomingBillsData
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
    Modifier
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp)
      .fillMaxSize(),

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
        0 -> CardPage()
        1 -> CardPage()
      }
    }
    Spacer(Modifier.height(150.dp))
  }
}