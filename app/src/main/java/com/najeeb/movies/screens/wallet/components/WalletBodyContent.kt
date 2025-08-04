package com.najeeb.movies.screens.wallet.components

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
import com.najeeb.movies.screens.home.HomeListItems
import com.najeeb.movies.ui.theme.ActiveTextToggleButtonColor
import com.najeeb.movies.ui.theme.BackgroundCardColor
import kotlinx.coroutines.launch


@Composable
fun WalletBodyContent(
  maxImageSize: Dp = 320.dp,
  minImageSize: Dp = 100.dp,
  onClickPay: () -> Unit
) {
  var currentImageSize by remember { mutableStateOf(maxImageSize) }
  var imageScale by remember { mutableFloatStateOf(1f) }
  val pagerState = rememberPagerState(
    initialPage = 0,
    pageCount = { 2 }
  )
  val coroutineScope = rememberCoroutineScope()

  val nestedScrollConnection = remember {
    object : NestedScrollConnection {
      override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        // Calculate the change in image size based on scroll delta
        val delta = available.y
        val newImageSize = currentImageSize + delta.dp
        val previousImageSize = currentImageSize

        // Constrain the image size within the allowed bounds
        currentImageSize = newImageSize.coerceIn(minImageSize, maxImageSize)
        val consumed = currentImageSize - previousImageSize

        // Calculate the scale for the image
        imageScale = currentImageSize / maxImageSize

        // Return the consumed scroll amount
        return Offset(0f, consumed.value)
      }
    }
  }

  Box(Modifier.nestedScroll(nestedScrollConnection)) {
    HorizontalPager(state = pagerState) { page ->
      when (page) {
        0 -> TransactionsListItems(currentImageSize)
        1 -> UpcomingBillsLazyColumn(currentImageSize, onClickPay)
      }
    }

    Column(
      Modifier
        .padding(horizontal = 16.dp)
        .size(maxImageSize)
        .align(Alignment.TopCenter)
        .graphicsLayer {
          scaleX = imageScale
          scaleY = imageScale
          // Center the image vertically as it scales
          translationY = -(maxImageSize.toPx() - currentImageSize.toPx()) / 2f
        },
      horizontalAlignment = Alignment.CenterHorizontally,

      ) {

      Spacer(Modifier.height(30.dp))
      TotalBalanceWithIcon()
      Spacer(Modifier.height(50.dp))
      ToggleButton(
        selectedIndex = pagerState.currentPage,
        onToggle = { page ->
          coroutineScope.launch {
            pagerState.animateScrollToPage(page)
          }
        },
        height = 55.dp,
        leftDescription = "Transactions",
        rightDescription = "Upcoming Bills",
        toggleColor = MaterialTheme.colorScheme.background,
        toggleBackgroundColor = BackgroundCardColor,
        toggleBorderColor = Color.Transparent,
        activeTextColor = ActiveTextToggleButtonColor,
        inactiveTextColor = ActiveTextToggleButtonColor,
      )
    }
  }
}

@Composable
fun TransactionsListItems(currentImageSize: Dp) {
  LazyColumn(
    Modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp)
      .offset { IntOffset(0, currentImageSize.roundToPx()) },

    ) {
    items(TransactionsData) { transaction ->
      HomeListItems(
        imageUri = transaction.imageUri,
        paddingImage = transaction.paddingImage,
        name = transaction.name,
        date = transaction.date,
        amount = transaction.amount,
        amountColor = transaction.amountColor,
      )
    }
  }
}

@Composable
fun UpcomingBillsLazyColumn(currentImageSize: Dp, onClickPay: () -> Unit) {
  LazyColumn(
    Modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp)
      .offset { IntOffset(0, currentImageSize.roundToPx()) },

    ) {
    items(UpcomingBillsData) { upcomingBills ->
      UpcomingBillsListItems(
        imageUri = upcomingBills.imageUri,
        paddingImage = upcomingBills.paddingImage,
        name = upcomingBills.name,
        date = upcomingBills.date,
        onClickPay = onClickPay
      )
    }
  }
}


