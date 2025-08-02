package com.najeeb.movies.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.najeeb.movies.R
import com.najeeb.movies.core.HelperSize.defaultPadding
import com.najeeb.movies.core.HelperSize.paddingBalanceCard
import com.najeeb.movies.ui.theme.toColor

@Composable
fun HomeScreen() {
  val systemUiController = rememberSystemUiController()
  SideEffect {
    systemUiController.setStatusBarColor(
      color = Color.Transparent,
      darkIcons = false
    )
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    containerColor = MaterialTheme.colorScheme.background,
//    contentWindowInsets = WindowInsets.systemBars
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Fixed Header (won't scroll)
      HomeHeader(
        username = "Enjelin Morgeana",
        balanceInfo = BalanceInfo(
          totalBalance = "\$2,548.00",
          income = "\$1,500.00",
          expense = "\$800.00"
        ),
        onNotificationClick = { /* Handle notification click */ },
        onMenuClick = { /* Handle menu click */ }
      )

      Spacer(modifier = Modifier.height(paddingBalanceCard.dp))

      // Scrollable Content (starts below the header)
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f) // Takes remaining space
          .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Spacer(modifier = Modifier.height(40.dp))

        SeeAllRow()

        // Transaction Items
        HomeListItems(
          imageUri = R.drawable.upwk,
          paddingImage = 1.dp,
          name = "Paypal",
          date = "Today",
          amount = "+ \$ 850.00",
          amountColor = "#25A969".toColor(),
        )
        HomeListItems(
          imageUri = R.drawable.girl_image,
          paddingImage = 10.dp,
          name = "Transfer",
          date = "Yesterday",
          amount = "- \$ 85.00",
          amountColor = "#F95B51".toColor(),
        )
        HomeListItems(
          imageUri = R.drawable.paypal,
          paddingImage = 1.dp,
          name = "Paypal",
          date = "Jan 30, 2022",
          amount = "+ \$ 1,406.00",
          amountColor = "#25A969".toColor(),
        )
        HomeListItems(
          imageUri = R.drawable.youtube,
          paddingImage = 10.dp,
          name = "Youtube",
          date = "Jan 16, 2022",
          amount = "- \$ 11.99",
          amountColor = "#F95B51".toColor(),
        )

        Spacer(modifier = Modifier.height(50.dp))
      }
    }
  }
}

@Composable
fun SeeAllRow() {

  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier
      .fillMaxWidth()
      .padding(
        horizontal = defaultPadding.dp,
      ),
  ) {
    Text(
      stringResource(id = R.string.transactions_history),
      style = MaterialTheme.typography.titleLarge,
    )
    Text(
      stringResource(id = R.string.see_all),
      style = MaterialTheme.typography.bodySmall,
      modifier = Modifier.clickable { /*TODO*/ }


    )
  }
}