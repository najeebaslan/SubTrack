package com.najeeb.movies.screens.home

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.najeeb.movies.R
import com.najeeb.movies.core.HelperSize.paddingBalanceCard
import com.najeeb.movies.data.defaultSendAgainImages
import com.najeeb.movies.data.defaultTransactions
import com.najeeb.movies.ui.theme.IconColor
import com.najeeb.movies.ui.theme.toColor
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.Line

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  username: String = "Enjelin Morgeana",
  balanceInfo: BalanceInfo = BalanceInfo(
    totalBalance = "\$2,548.00",
    income = "\$1,500.00",
    expense = "\$800.00"
  ),
  transactions: List<TransactionItem> = defaultTransactions,
  sendAgainImages: List<Int> = defaultSendAgainImages,
  onNotificationClick: () -> Unit = {},
  onMenuClick: () -> Unit = {}
) {
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

    topBar = {
      CenterAlignedTopAppBar(
        title = {},
        navigationIcon = {
          Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            UserGreeting(username)
          }
        },
        actions = {
          Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            NotificationIcon(onClick = onNotificationClick)
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Color.Transparent,
          actionIconContentColor = IconColor
        )
      )
    }

  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      HomeHeader(
        username = username,
        balanceInfo = balanceInfo,
        onNotificationClick = onNotificationClick,
        onMenuClick = onMenuClick
      )

      Spacer(modifier = Modifier.height(paddingBalanceCard.dp))

      Column(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f)
          .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
      ) {
        Spacer(modifier = Modifier.height(40.dp))

        TransactionsSection(transactions)

        SendAgainSection(sendAgainImages)

      }
    }
  }
}
